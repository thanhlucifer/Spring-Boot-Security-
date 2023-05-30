package com.example.asm7_phamvanthanh.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer role_id;
    @Column
    private String name;
}