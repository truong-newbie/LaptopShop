package com.example.laptopshop_project.controller.client;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemProductController {

    @GetMapping("/product/detail/{id}")
    public String getDetailProduct(Model model, @PathVariable long id){
        return "client/product/detail";
    }

}
