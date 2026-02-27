package com.trinity.manneger_control.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trinity.manneger_control.domain.Faixas;
import com.trinity.manneger_control.entity.Teacher;
import com.trinity.manneger_control.interfaces.TeacherInterface;
import com.trinity.manneger_control.rabbitmq.TeacherEventPublisher;
import com.trinity.manneger_control.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherInterface {

    private final TeacherRepository teacherRepository;
    private final TeacherEventPublisher teacherEventPublisher;

    @Override
    public Teacher criar(Teacher teacher) {
        teacher.setId(null);
        teacher.setUserId(null);

        Teacher saved = teacherRepository.save(teacher);

        teacherEventPublisher.publishTeacherCreated(saved);

        return saved;
    }

    @Override
    public List<Teacher> listarTodos() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher buscarPorId(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher não encontrado com ID: " + id));
    }

    @Override
    public Teacher atualizar(Long id, Teacher teacherAtualizado) {

        Teacher teacherExistente = buscarPorId(id);

        teacherExistente.setName(teacherAtualizado.getName());
        teacherExistente.setEmail(teacherAtualizado.getEmail());
        teacherExistente.setPhone(teacherAtualizado.getPhone());
        teacherExistente.setAddress(teacherAtualizado.getAddress());
        teacherExistente.setBranchId(teacherAtualizado.getBranchId());
        teacherExistente.setAcademicId(teacherAtualizado.getAcademicId());
        teacherExistente.setActive(teacherAtualizado.getActive());
        teacherExistente.setUserId(teacherAtualizado.getUserId());

        return teacherRepository.save(teacherExistente);
    }

    @Override
    public void deletar(Long id) {
        Teacher teacher = buscarPorId(id);
        teacherRepository.delete(teacher);
    }

    @Override
    public Teacher updateQuantidadeGraus(Long id, Integer quantidadeGraus) {
        Integer quantidadeGrausVerificada = verifyQuantidadeGraus(id, quantidadeGraus);
        Teacher teacherExistente = buscarPorId(id);
        teacherExistente.setQuantityDegree(quantidadeGrausVerificada);
        return teacherRepository.save(teacherExistente);
    }

    @Override
    public Teacher updateFaixa(Long id, Faixas faixaEtaria) {
        Teacher teacherExistente = buscarPorId(id);
        teacherExistente.setBelt(faixaEtaria);
        return teacherRepository.save(teacherExistente);
    }

    @Override
    public List<Teacher> getTeachersByAcademiaId(Long academiaId) {
        return teacherRepository.findByAcademicId(academiaId);
    }

    @Override
    public List<Teacher> getTeachersByBranchIdAndAcademiaId(Long branchId, Long academiaId) {
        return teacherRepository.findByBranchIdAndAcademicId(branchId, academiaId);
    }

    public Teacher updateActive(Long id, Boolean ativo) {
        Teacher teacherExistente = buscarPorId(id);
        teacherExistente.setActive(ativo);
        return teacherRepository.save(teacherExistente);
    }

    public Integer verifyQuantidadeGraus(Long id, Integer quantidadeGraus) {
        Teacher teacher = buscarPorId(id);
        switch (teacher.getBelt()) {
            case PRETA:
                if (teacher.getQuantityDegree() > 6)
                    throw new IllegalArgumentException("Teachers Black Belts does´t have more than 6 grades");
                return teacher.getQuantityDegree() + 1;
            default:
                if (quantidadeGraus == 4 && teacher.getQuantityDegree() == 4)
                    throw new IllegalArgumentException("Teachers Belts does´t have more than 4 grades");
                return teacher.getQuantityDegree() + 1;
        }
    }
}
