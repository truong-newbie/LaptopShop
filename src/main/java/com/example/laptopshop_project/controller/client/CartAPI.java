package com.example.laptopshop_project.controller.client;


import com.example.laptopshop_project.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Setter
@Getter
class CartRequest {
    private long quantity;
    private long productId;
}

@RestController
public class CartAPI {
    private final ProductService productService;

    public CartAPI(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/api/add-product-to-cart")
    public ResponseEntity<Integer> addProductToCart(
            @RequestBody() CartRequest cartRequest,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        this.productService.handleAddProductToCart(email, cartRequest.getProductId(), session,
                cartRequest.getQuantity());
        int sum = (int) session.getAttribute("sum");
        return ResponseEntity.ok().body(sum);
    }
}
