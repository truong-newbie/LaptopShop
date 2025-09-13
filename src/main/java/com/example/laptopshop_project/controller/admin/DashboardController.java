package com.example.laptopshop_project.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/admin")
    public String GetDashboard() {
        return "admin/dashboard/show";
    }
}
