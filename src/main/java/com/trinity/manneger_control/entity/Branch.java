package com.trinity.manneger_control.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "branch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Branch {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String phone;
    private Boolean active;
}
