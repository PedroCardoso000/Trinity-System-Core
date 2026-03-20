package com.trinity.manneger_control.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trinity.manneger_control.domain.Faixas;
import com.trinity.manneger_control.entity.GraduationRules;

public interface GraduationRulesRepository extends JpaRepository<GraduationRules, Long> {
    GraduationRules findByFaixa(Faixas faixa);
}
