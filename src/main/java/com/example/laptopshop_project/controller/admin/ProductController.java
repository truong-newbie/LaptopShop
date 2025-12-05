package com.example.laptopshop_project.controller.admin;

import com.example.laptopshop_project.domain.Products;
import com.example.laptopshop_project.repository.ProductRepository;
import com.example.laptopshop_project.service.ProductService;
import com.example.laptopshop_project.service.UploadService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(UploadService uploadService, ProductService productService, ProductRepository productRepository) {
        this.uploadService = uploadService;
        this.productService = productService;
    }

    @GetMapping("/admin/product")
    public String getProduct(Model model,
                             @RequestParam("page") Optional<String> pageOptional) {
        int page = 1;
        try {
            if (pageOptional.isPresent()) {
                //convert from string to int
                page = Integer.parseInt(pageOptional.get());
            } else {
                //page=1
            }
        } catch (Exception e) {
            //page=1
            //todo1 : handle exceptoin
        }
        Pageable pageable = PageRequest.of(page - 1, 2);
        Page<Products> products = productService.getAllProducts(pageable);
        List<Products> listProducts = products.getContent();
        model.addAttribute("products", listProducts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", products.getTotalPages());
        return "admin/product/show";
    }

    @GetMapping("admin/product/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Products());
        return "admin/product/create";
    }

    @PostMapping("admin/product/create")
    public String createProduct(Model model, @ModelAttribute("newProduct") @Valid Products product,
                                BindingResult newProductBindingResult,
                                @RequestParam("TruongFile") MultipartFile file) {

        //validate
        if (newProductBindingResult.hasErrors()) {
            // Trả về form để hiển thị lỗi, tránh cho Hibernate ném ConstraintViolationException
            return "admin/product/create";
        }
        String image = this.uploadService.handleSaveUploadFile(file, "product");
        product.setImage(image);
        this.productService.hanleSave(product);
        return "redirect:/admin/product";
    }

    //xem chi tiet san pham
    @GetMapping("/admin/product/{id}")
    public String getDetailProduct(Model model, @PathVariable Long id) {
        Products product = this.productService.getProductById(id).get();
        model.addAttribute("product", product);
        return "admin/product/detail";
    }

    //update product
    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProduct(Model model, @PathVariable Long id) {
        Products product = this.productService.getProductById(id).get();
        model.addAttribute("updateProduct", product);
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update/{id}")
    public String updateProduct(Model model, @PathVariable Long id,
                                @ModelAttribute("updateProduct") @Valid Products updateProduct,
                                BindingResult newProductBindingResult,
                                @RequestParam("TruongFile") MultipartFile file
    ) {
        if (newProductBindingResult.hasErrors()) {
            return "admin/product/update";
        }
        if (!file.isEmpty()) {
            String image = this.uploadService.handleSaveUploadFile(file, "product");
            updateProduct.setImage(image);
        }
        productService.updateProduct(id, updateProduct);
        return "redirect:/admin/product";
    }

    //delete
    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProduct(Model model, @PathVariable long id) {
        Products product = productService.getProductById(id).get();
        model.addAttribute("deleteProduct", product);
        return "admin/product/delete";
    }

    @PostMapping("/admin/product/delete/{id}")
    public String deleteProduct(Model model, @PathVariable long id) {
        this.productService.deleteProductById(id);
        return "redirect:/admin/product";
    }
}
