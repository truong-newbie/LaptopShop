package com.example.laptopshop_project.controller.client;

import com.example.laptopshop_project.domain.CartDetail;
import com.example.laptopshop_project.service.CartDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    private final CartDetailService cartDetailService;

    public CartController(CartDetailService cartDetailService) {
        this.cartDetailService = cartDetailService;
    }

    @PostMapping("/cart/update")
    @ResponseBody
    public ResponseEntity<?> updateQuantity(
            @RequestParam Long id,
            @RequestParam int quantity) {

        CartDetail cartDetail = cartDetailService.getById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy cartDetail"));


        if (quantity <= 0) {
            return ResponseEntity.badRequest().body("Quantity must be > 0");
        }

        cartDetail.setQuantity(quantity);
        cartDetailService.handleSave(cartDetail);

        double total = cartDetailService.calculateTotal(cartDetail.getCart().getId());

        return ResponseEntity.ok(total);
    }

}
