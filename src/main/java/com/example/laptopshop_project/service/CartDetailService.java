package com.example.laptopshop_project.service;

import com.example.laptopshop_project.domain.CartDetail;
import com.example.laptopshop_project.repository.CartDetailRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartDetailService {
    private final CartDetailRepository cartDetailRepository;

    public CartDetailService(CartDetailRepository cartDetailRepository) {
        this.cartDetailRepository = cartDetailRepository;
    }

    public Optional<CartDetail> getById(Long id) {
        return this.cartDetailRepository.findById(id);
    }

    public void handleSave(CartDetail cartDetail) {
        this.cartDetailRepository.save(cartDetail);
    }

    public double calculateTotal(Long cartId) {
        List<CartDetail> details = new ArrayList<>();
        Optional<CartDetail> optional = this.cartDetailRepository.findById(cartId);
        
        if (optional.isPresent()) {
            details.add(optional.get());
        }

        double total = 0;
        for (CartDetail detail : details) {
            total += detail.getQuantity() * detail.getPrice();
        }

        return total;
    }

}
