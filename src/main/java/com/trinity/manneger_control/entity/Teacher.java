package com.trinity.manneger_control.entity;

import java.util.List;

import com.trinity.manneger_control.domain.Faixas;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, comment = "Name of the teacher")
    private String name;

    @Column(nullable = false, comment = "Email of the teacher")
    private String email;

    @Column(nullable = false, comment = "Phone number of the teacher")
    private String phone;

    @Column
    private String address;

    private String userId;

    @Column(nullable = false, comment = "Age range of the teacher")
    private Faixas belt;

    private Integer quantityDegree;

    @Column(nullable = false, comment = "IDs of the branches to which the teacher is associated")
    private List<Long> branchId;

    @Column(nullable = false, comment = "ID of the academic to which the teacher is associated")
    private Long academicId;

    @Column(nullable = false, comment = "Teacher Active")
    private Boolean active;
}
