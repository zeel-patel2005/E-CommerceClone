package org.example.ecommerce.controller;

import org.example.ecommerce.model.Cart;
import org.example.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(
            @RequestParam String userEmail,
            @RequestParam Long productId,
            @RequestParam int quantity) {

        String result = cartService.addToCart(userEmail, productId, quantity);
        if ("Item already in cart".equals(result)) {
            return ResponseEntity.status(200).body(result);
        } else if ("Product not found".equals(result)) {
            return ResponseEntity.status(404).body(result);
        } else {
            return ResponseEntity.status(201).body(result); // 201 for created
        }
    }
}
