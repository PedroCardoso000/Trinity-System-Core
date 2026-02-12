package com.trinity.manneger_control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trinity.manneger_control.entity.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> { 

}   