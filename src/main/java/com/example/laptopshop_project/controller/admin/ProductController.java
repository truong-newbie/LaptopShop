package com.example.laptopshop_project.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/admin/product")
    public String GetProduct() {
        return "admin/product/show";
    }
}
