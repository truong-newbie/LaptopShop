package com.example.laptopshop_project.controller.client;


import com.example.laptopshop_project.domain.Cart;
import com.example.laptopshop_project.domain.CartDetail;
import com.example.laptopshop_project.domain.Products;
import com.example.laptopshop_project.domain.Users;
import com.example.laptopshop_project.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemProductController {
    private final ProductService productService;

    public ItemProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String getDetailProduct(Model model, @PathVariable long id) {
        Products product = productService.getProductById(id).get();
        model.addAttribute("product", product);
        model.addAttribute("id", id);
        return "client/product/detail";
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String addProductToCart(@PathVariable long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        long productId = id;
        this.productService.handleAddProductToCart(email, productId, session);
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String getCardPage(Model model, HttpServletRequest req) {
        Users user = new Users();
        HttpSession session = req.getSession(false);
        long id = (long) session.getAttribute("id");
        user.setId(id);

        Cart cart = this.productService.fetchByUser(user);
        List<CartDetail> cartDetailList = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetail();
        double totalPrice = 0;
        for (CartDetail cartDetail : cartDetailList) {
            totalPrice += cartDetail.getPrice();
        }
        model.addAttribute("cartDetails", cartDetailList);
        model.addAttribute("totalPrice", totalPrice);
        return "client/cart/show";
    }

    @PostMapping("/delete-cart-product/{id}")
    public String deleteCartDetail(@PathVariable long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        long cartDetailId = id;
        this.productService.handleRemoveCartDetail(cartDetailId, session);
        return "redirect:/cart";
    }
}
