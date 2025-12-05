package com.example.laptopshop_project.service;

import com.example.laptopshop_project.domain.OrderDetail;
import com.example.laptopshop_project.repository.OrderDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<OrderDetail> getOrderDetailById(long id) {
        return this.orderDetailRepository.findById(id);
    }
}
