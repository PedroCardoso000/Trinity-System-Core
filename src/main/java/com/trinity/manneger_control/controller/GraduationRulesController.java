package com.trinity.manneger_control.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trinity.manneger_control.entity.GraduationRules;
import com.trinity.manneger_control.service.GraduationRulesServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class GraduationRulesController {

    private final GraduationRulesServiceImpl graduationRulesServiceImpl;

    @PostMapping("/graduation-rules")
    public GraduationRules create(@RequestBody GraduationRules graduationRules) {
        return graduationRulesServiceImpl.create(graduationRules);
    }

    @GetMapping("/graduation-rules/{id}")
    public GraduationRules getById(@PathVariable Long id) {
        return graduationRulesServiceImpl.getById(id);

    }

    @PutMapping("/graduation-rules")
    public GraduationRules update(@RequestBody GraduationRules graduationRules) {
        return graduationRulesServiceImpl.update(graduationRules);
    }

    @DeleteMapping("/graduation-rules/{id}")
    public void delete(@PathVariable Long id) {
        graduationRulesServiceImpl.delete(id);
    }
}
