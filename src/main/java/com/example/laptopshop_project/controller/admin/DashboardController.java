package com.example.laptopshop_project.controller.admin;

import com.example.laptopshop_project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    private final UserService userService;

    public DashboardController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String GetDashboard(Model model) {
        model.addAttribute("countUsers", this.userService.countUsers());
        model.addAttribute("countProducts", this.userService.countProducts());
        model.addAttribute("countOrders", this.userService.countOrders());

        model.addAttribute("doanhThuThang", 35000000);
        model.addAttribute("pendingOrders", 12);
        model.addAttribute("bestSellerName", "MacBook Air M1");


        return "admin/dashboard/show";
    }
}
