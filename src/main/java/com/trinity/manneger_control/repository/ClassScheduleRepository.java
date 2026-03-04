package com.trinity.manneger_control.repository;

import com.trinity.manneger_control.entity.ClassSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassScheduleRepository extends JpaRepository<ClassSchedule, Long> {
    List<ClassSchedule> findByBranchId(Long branchId);

    List<ClassSchedule> findByBranchIdAndActiveTrue(Long branchId);
}
