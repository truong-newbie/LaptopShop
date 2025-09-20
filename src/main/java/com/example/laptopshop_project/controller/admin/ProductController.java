package com.example.laptopshop_project.controller.admin;

import com.example.laptopshop_project.domain.Products;
import com.example.laptopshop_project.domain.Users;
import com.example.laptopshop_project.service.UploadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductController {
    private final UploadService uploadService;

    public ProductController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @GetMapping("/admin/product")
    public String getProduct() {
        return "admin/product/show";
    }

    @GetMapping("admin/product/create")
    public String getCreateProductPage(Model model){
        model.addAttribute("newProduct", new Products());
        return "admin/product/create";
    }

    @PostMapping("admin/product/create")
    public String createProduct(Model model , @ModelAttribute("newProduct") Products product,
                                @RequestParam("TruongFile") MultipartFile file){
        String image= this.uploadService.handleSaveUploadFile(file,"product");
        product.setImage(image);
        return "redirect:/admin/product";
    }
}
