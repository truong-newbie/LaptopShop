package com.example.laptopshop_project.service;

import com.example.laptopshop_project.domain.OrderDetail;
import com.example.laptopshop_project.domain.Orders;
import com.example.laptopshop_project.domain.Users;
import com.example.laptopshop_project.repository.OrderDetailRepository;
import com.example.laptopshop_project.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<Orders> getAll() {
        return this.orderRepository.findAll();
    }

    public Optional<Orders> fetchOrderById(long id) {
        return this.orderRepository.findById(id);
    }

    public void deleteOrderById(long id) {
        Optional<Orders> ordersOptional = this.orderRepository.findById(id);
        if (ordersOptional.isPresent()) {
            Orders orders = ordersOptional.get();
            List<OrderDetail> orderDetails = orders.getOrderDetails();
            for (OrderDetail orderDetail : orderDetails) {
                this.orderDetailRepository.deleteById(orderDetail.getId());
            }
        }
        this.orderRepository.deleteById(id);
    }

    public void updateOrder(Orders order) {
        Optional<Orders> orderOptional = this.fetchOrderById(order.getId());
        if (orderOptional.isPresent()) {
            Orders currentOrder = orderOptional.get();
            currentOrder.setStatus(order.getStatus());
            this.orderRepository.save(currentOrder);
        }
    }


    public List<Orders> fetchOrderByUser(Users user) {
        return this.orderRepository.findByUser(user);
    }
}
