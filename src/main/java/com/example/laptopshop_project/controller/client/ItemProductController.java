package com.example.laptopshop_project.controller.client;


import com.example.laptopshop_project.domain.Products;
import com.example.laptopshop_project.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemProductController {
    private final ProductService productService;
    public ItemProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String getDetailProduct(Model model, @PathVariable long id){
        Products product = productService.getProductById(id).get();
        model.addAttribute("product", product);
        model.addAttribute("id", id);
        return "client/product/detail";
    }



}
