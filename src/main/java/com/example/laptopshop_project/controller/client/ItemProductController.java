package com.example.laptopshop_project.controller.client;


import com.example.laptopshop_project.domain.Cart;
import com.example.laptopshop_project.domain.CartDetail;
import com.example.laptopshop_project.domain.Products;
import com.example.laptopshop_project.domain.Users;
import com.example.laptopshop_project.repository.UserRepository;
import com.example.laptopshop_project.service.ProductService;
import com.example.laptopshop_project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ItemProductController {
    private final ProductService productService;
    private final UserRepository userRepository;
    private final UserService userService;

    public ItemProductController(ProductService productService, UserRepository userRepository,
                                 UserService userService) {
        this.productService = productService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/products")
    public String getProductPage(Model model,
                                 @RequestParam("page") Optional<String> pageOptional,
                                 @RequestParam("name") Optional<String> nameOptional) {
        int page = 1;
        try {
            if (pageOptional.isPresent()) {
                //convert string to int
                page = Integer.parseInt(pageOptional.get());
            } else {
                //page=1
            }
        } catch (Exception e) {
            //page=1
            //handle ex
        }

        Pageable pageable = PageRequest.of(page - 1, 6);
        String name = nameOptional.isPresent() ? nameOptional.get() : "";
        Page<Products> products = productService.getAllProductsWithSpec(pageable, name);
        List<Products> listProducts = products.getContent();

        model.addAttribute("products", listProducts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", products.getTotalPages());
        return "client/homepage/product";
    }

    @GetMapping("/product/{id:[0-9]+}")
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
        this.productService.handleAddProductToCart(email, productId, session, 1);
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String getCardPage(Model model, HttpServletRequest req) {
        Users user = new Users();
        HttpSession session = req.getSession(false);
        long id = (long) session.getAttribute("id");
        user.setId(id);

        Cart cart = this.productService.fetchByUser(user);
        List<CartDetail> cartDetails = cart == null ? new ArrayList<>() : cart.getCartDetails();
        double totalPrice = 0;
        for (CartDetail cartDetail : cartDetails) {
            totalPrice += cartDetail.getQuantity() * cartDetail.getPrice();
        }
        model.addAttribute("cartDetails", cartDetails);
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

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/confirm-chechout")
    public String getCheckOutPage(@ModelAttribute("cart") Cart cart) {
        List<CartDetail> cartDetails = cart == null ? new ArrayList<>() : cart.getCartDetails();
        this.productService.handleUpdateCartBeforeCheckout(cartDetails);
        return "redirect:/checkout";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/place-order")
    public String handlePlaceOrder(
            HttpServletRequest request,
            @RequestParam("receiverName") String receiverName,
            @RequestParam("receiverAddress") String receiverAddress,
            @RequestParam("receiverPhone") String receiverPhone) {
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        Users currentUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        this.productService.handlePlaceOrder(currentUser, session, receiverName, receiverAddress, receiverPhone);
        return "redirect:/thank";
    }

    @GetMapping("/thank")
    public String getThankYouPage(Model model) {
        return "client/cart/thanks";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/checkout")
    public String GetCheckout(Model model, HttpServletRequest req) {
//        Users user = new Users();
        HttpSession session = req.getSession(false);
        long id = (long) session.getAttribute("id");
//        user.setId(id);

        Users user = userService.getUserById(id);

        Cart cart = this.productService.fetchByUser(user);

        List<CartDetail> cartDetails = cart == null ? new ArrayList<>() : cart.getCartDetails();
        double totalPrice = 0;
        for (CartDetail cartDetail : cartDetails) {
            totalPrice += cartDetail.getQuantity() * cartDetail.getPrice();
        }
        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("totalPrice", totalPrice);
        return "client/cart/checkout";
    }


    @PostMapping("/add-product-from-view-detail")
    public String handleAddProductFromViewDetail(
            @RequestParam("id") long id,
            @RequestParam("quantity") long quantity,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        this.productService.handleAddProductToCart(email, id, session, quantity);
        return "redirect:/product/" + id;

    }
}
