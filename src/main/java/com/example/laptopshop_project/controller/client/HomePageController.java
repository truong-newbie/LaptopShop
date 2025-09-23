package com.example.laptopshop_project.controller.client;


import com.example.laptopshop_project.domain.Products;
import com.example.laptopshop_project.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomePageController {

    private final ProductService productService;
    public HomePageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getHomePage(Model model){
        List<Products> products= productService.getAllProducts();
        model.addAttribute("products", products);
        return "client/homepage/show";
    }
}
