package com.example.asm7_phamvanthanh.controller;

import com.example.asm7_phamvanthanh.model.Product;
import com.example.asm7_phamvanthanh.service.CategoryService;
import com.example.asm7_phamvanthanh.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String index(Model model)
    {
        model.addAttribute("listproduct", productService.GetAll());
        return "products/index.html";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model)
    {
        model.addAttribute("product", productService.get(id));
        model.addAttribute("listCategory", categoryService.GetAll() );
        return "/products/edit";
    }
    @PostMapping("/edit")
    public String edit(@Valid Product editProduct,
                       @RequestParam MultipartFile imageProduct,
                       BindingResult result,
                       Model model)
    {
        if (result.hasErrors()) {
            model.addAttribute("product", editProduct);
            model.addAttribute("listCategory", categoryService.GetAll() );
            return "/products/edit";
        }
        if(imageProduct != null && imageProduct.getSize() > 0)
        {
            try {
                File saveFile = new ClassPathResource("static/images").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + editProduct.getImage());
                Files.copy(imageProduct.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        productService.edit(editProduct);
        return "redirect:/products";
    }
}
