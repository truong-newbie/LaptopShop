package com.example.laptopshop_project.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping("/admin/order")
    public String GetOrder() {
        return "admin/order/show";
    }
}
