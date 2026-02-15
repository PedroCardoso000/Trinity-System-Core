package com.trinity.manneger_control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trinity.manneger_control.entity.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    
}
