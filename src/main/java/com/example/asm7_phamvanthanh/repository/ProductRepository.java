package com.example.asm7_phamvanthanh.repository;

import com.example.asm7_phamvanthanh.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}