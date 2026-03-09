package com.trinity.manneger_control.service;

import com.trinity.manneger_control.entity.ClassRoom;
import com.trinity.manneger_control.entity.ClassSchedule;
import com.trinity.manneger_control.repository.ClassRoomRepository;
import com.trinity.manneger_control.repository.ClassScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassScheduleServiceImpl {

    private final ClassScheduleRepository classScheduleRepository;
    private final ClassRoomRepository classRoomRepository;

    public ClassSchedule create(ClassSchedule schedule) {

        schedule.setGeneratedUntil(null);

        ClassSchedule saved = classScheduleRepository.save(schedule);

        generateNext(saved.getId()); // gera primeiros 3 meses

        return getById(saved.getId());
    }

    public ClassSchedule getById(Long id) {
        return classScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
    }

    public List<ClassSchedule> getAll() {
        return classScheduleRepository.findAll();
    }

    public List<ClassSchedule> getByBranchAndAcademic(Long branchId, Long academicId) {
        return classScheduleRepository.findByBranchIdAndAcademicId(branchId, academicId);
    }

    public ClassSchedule update(Long id, ClassSchedule updated) {

        ClassSchedule existing = getById(id);

        existing.setName(updated.getName());
        existing.setDayOfWeek(updated.getDayOfWeek());
        existing.setTime(updated.getTime());
        existing.setStartDate(updated.getStartDate());
        existing.setEndDate(updated.getEndDate());
        existing.setActive(updated.getActive());
        existing.setBranchId(updated.getBranchId());
        existing.setAcademicId(updated.getAcademicId());

        return classScheduleRepository.save(existing);
    }

    public void delete(Long id) {
        classScheduleRepository.deleteById(id);
    }

    // POST /class-schedules/{id}/generate-next
    public void generateNext(Long id) {

        ClassSchedule schedule = getById(id);

        if (!schedule.getActive()) {
            throw new RuntimeException("Schedule is not active");
        }

        generateClassRooms(schedule);
    }

    private void generateClassRooms(ClassSchedule schedule) {

        LocalDate generationStart;

        // 🔹 Se nunca gerou antes
        if (schedule.getGeneratedUntil() == null) {
            generationStart = schedule.getStartDate();
        } else {
            generationStart = schedule.getGeneratedUntil().plusDays(1);
        }

        // 🔹 Sempre gera +3 meses a partir do ponto atual
        LocalDate generationEnd = generationStart.plusMonths(3);

        // 🔹 Se existir endDate, respeita limite
        if (schedule.getEndDate() != null &&
                generationEnd.isAfter(schedule.getEndDate())) {

            generationEnd = schedule.getEndDate();
        }

        // 🔹 Se já passou do limite, não gera nada
        if (generationStart.isAfter(generationEnd)) {
            return;
        }

        LocalDate currentDate = generationStart;

        while (!currentDate.isAfter(generationEnd)) {

            if (currentDate.getDayOfWeek().equals(schedule.getDayOfWeek())) {

                LocalDateTime dateTime =
                        LocalDateTime.of(currentDate, schedule.getTime());

                boolean alreadyExists =
                        classRoomRepository
                                .existsByScheduleIdAndDateTime(
                                        schedule.getId(),
                                        dateTime
                                );

                if (!alreadyExists) {

                    ClassRoom classRoom = new ClassRoom();
                    classRoom.setScheduleId(schedule.getId());
                    classRoom.setDateTime(dateTime);
                    classRoom.setCancelled(false);
                    classRoom.setBranchId(schedule.getBranchId());
                    classRoom.setAcademicId(schedule.getAcademicId());

                    classRoomRepository.save(classRoom);
                }
            }

            currentDate = currentDate.plusDays(1);
        }

        schedule.setGeneratedUntil(generationEnd);
        classScheduleRepository.save(schedule);
    }
}
