package com.example.asm7_phamvanthanh.repository;
import com.example.asm7_phamvanthanh.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

