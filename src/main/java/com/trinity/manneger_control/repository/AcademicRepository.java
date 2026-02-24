package com.trinity.manneger_control.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trinity.manneger_control.entity.Academic;


public interface AcademicRepository extends JpaRepository<Academic, Long> {
    
}
