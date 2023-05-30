package com.example.asm7_phamvanthanh.service;

import com.example.asm7_phamvanthanh.model.Category;
import com.example.asm7_phamvanthanh.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> GetAll() {
        return categoryRepository.findAll();
    }
}

