package com.example.asm7_phamvanthanh.service;

import com.example.asm7_phamvanthanh.model.CartItem;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class ShoppingCartServiceImp implements ShoppingCartService{
    Map<Integer, CartItem> shoppingCart = new HashMap<>();

    @Override
    public void add(CartItem newItem){
        CartItem cartItem = shoppingCart.get(newItem.getProductId());
        if(cartItem == null) {
            shoppingCart.put(newItem.getProductId(),newItem);

        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
    }

    @Override
    public void remove(int id) {
        shoppingCart.remove(id);
    }

    @Override
    public CartItem update(int productID, int quantity) {
        CartItem cartItem = shoppingCart.get(productID);
        cartItem.setQuantity(quantity);
        return cartItem;
    }

    @Override
    public void clear() {
        shoppingCart.clear();
    }

    @Override
    public Collection<CartItem> getAllItems(){
        return shoppingCart.values();
    }

    @Override
    public int getCount() {
        return shoppingCart.values().size();
    }

    @Override
    public double getAmount() {
        return shoppingCart.values().stream().mapToDouble(item -> item.getQuantity()*item.getPrice()).sum();
    }
}
