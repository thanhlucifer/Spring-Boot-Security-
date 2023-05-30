package com.example.asm7_phamvanthanh.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private int productId;
    private String name;
    private double price;
    private double quantity = 1;

}