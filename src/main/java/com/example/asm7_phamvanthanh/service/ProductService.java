package com.example.asm7_phamvanthanh.service;


import com.example.asm7_phamvanthanh.model.Product;
import com.example.asm7_phamvanthanh.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> GetAll() {
        return productRepository.findAll();
    }

    public void add(Product newProduct) {
        productRepository.save(newProduct);
    }

    public Product get(int id) {
        return productRepository.findById(id).stream().findFirst().orElse(null);
    }

    public void edit(Product editProduct) {
        Product find = get(editProduct.getId());
        if(find!=null) {
            ///or implement clon()
            find.setName(editProduct.getName());
            find.setImage(editProduct.getImage());
            find.setPrice(editProduct.getPrice());
            //    productRepository.save(find);
        }
    }
}
