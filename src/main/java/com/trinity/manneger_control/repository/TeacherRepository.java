package com.trinity.manneger_control.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trinity.manneger_control.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByAcademicId(Long academicId);

    List<Teacher> findByBranchIdAndAcademicId(Long branchId, Long academicId);

    Optional<Teacher> findByEmail(String email);
}
