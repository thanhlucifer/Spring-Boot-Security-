package com.example.asm7_phamvanthanh.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class UserRoleId implements Serializable {
    @Column(name = "user_id")
    private Integer user;

    @Column(name = "role_id")
    private Integer role;
}
