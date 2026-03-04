package com.trinity.manneger_control.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trinity.manneger_control.entity.ClassRoom;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {

    List<ClassRoom> findByBranchId(Long branchId);

    List<ClassRoom> findByScheduleId(Long scheduleId);
    
    boolean existsByScheduleIdAndDateTime(Long scheduleId, LocalDateTime dateTime);

    List<ClassRoom> findByBranchIdAndDateTimeBetween(
            Long branchId,
            LocalDateTime start,
            LocalDateTime end);
}
