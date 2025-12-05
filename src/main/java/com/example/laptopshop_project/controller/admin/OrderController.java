package com.example.laptopshop_project.controller.admin;

import com.example.laptopshop_project.domain.Orders;
import com.example.laptopshop_project.service.OrderDetailService;
import com.example.laptopshop_project.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

    public OrderController(OrderService orderService, OrderDetailService orderDetailService) {
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/admin/order")
    public String GetOrder(Model model) {
        List<Orders> orders = this.orderService.getAll();
        model.addAttribute("orders", orders);
        return "admin/order/show";
    }

    @GetMapping("admin/order/{id}")
    public String getViewOrderDetail(Model model, @PathVariable long id) {
        Orders order = this.orderService.fetchOrderById(id).get();
        model.addAttribute("order", order);
        model.addAttribute("id", id);
        model.addAttribute("orderDetails", order.getOrderDetails());
        return "admin/order/view";
    }

    @GetMapping("/admin/order/update/{id}")
    public String getUpdateOrder(Model model, @PathVariable long id) {
        Optional<Orders> currentOrder = this.orderService.fetchOrderById(id);
        model.addAttribute("newOrder", currentOrder.get());
        return "admin/order/update";
    }

    @PostMapping("/admin/order/update")
    public String handleUpdateOrder(@ModelAttribute("newOrder") Orders order) {
        this.orderService.updateOrder(order);
        return "redirect:/admin/order";
    }

    @GetMapping("/admin/order/delete/{id}")
    public String getDeleteOrder(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("newOrder", new Orders());
        return "admin/order/delete";
    }

    @PostMapping("/admin/order/delete")
    public String handleDeleteOrder(@ModelAttribute("newOrder") Orders order) {
        this.orderService.deleteOrderById(order.getId());
        return "redirect:/admin/order";
    }

}
