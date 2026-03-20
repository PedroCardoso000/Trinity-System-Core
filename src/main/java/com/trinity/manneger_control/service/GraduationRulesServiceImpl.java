package com.trinity.manneger_control.service;

import org.springframework.stereotype.Service;

import com.trinity.manneger_control.entity.GraduationRules;
import com.trinity.manneger_control.repository.GraduationRulesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GraduationRulesServiceImpl {
    private final GraduationRulesRepository graduationRulesRepository;

    public GraduationRules create(GraduationRules graduationRules) {
        if (graduationRulesRepository.findByFaixa(graduationRules.getFaixa()) != null) {
            throw new IllegalArgumentException("Graduation rules already exists");
        }
        return graduationRulesRepository.save(graduationRules);
    }

    public GraduationRules getById(Long id) {
        return graduationRulesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Graduation rules not found"));
    }

    public GraduationRules update(GraduationRules graduationRules) {
        if (graduationRulesRepository.findById(graduationRules.getId()).isEmpty()) {
            throw new IllegalArgumentException("Graduation rules not found");
        }
        return graduationRulesRepository.save(graduationRules);
    }

    public void delete(Long id) {
        graduationRulesRepository.deleteById(id);
    }
}
