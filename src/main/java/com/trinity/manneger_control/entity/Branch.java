package com.trinity.manneger_control.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "branch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, comment = "Nome da branch")
    private String name;
    private String address;
    @Column(nullable = false, comment = "Cidade da branch")
    private String city;
    private String state;
    private String country;
    @Column(nullable = false, comment = "Código postal da branch")
    private String zipCode;
    private String phone;
    @Column(nullable = false, comment = "Indica se a branch está ativa")
    private Boolean active;
    @Column
    private Long academicId;
}
