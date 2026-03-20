package com.trinity.manneger_control.entity;

import com.trinity.manneger_control.domain.Faixas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "graduation_rules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GraduationRules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Faixas faixa;
    private int requiredLessons;

}
