package com.example.laptopshop_project.service;

import com.example.laptopshop_project.domain.Cart;
import com.example.laptopshop_project.domain.CartDetail;
import com.example.laptopshop_project.domain.Products;
import com.example.laptopshop_project.domain.Users;
import com.example.laptopshop_project.repository.CartDetailRepository;
import com.example.laptopshop_project.repository.CartRepository;
import com.example.laptopshop_project.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
                          CartDetailRepository cartDetailRepository,
                          UserService userService) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
    }

    public Products hanleSave(Products products) {
        return this.productRepository.save(products);
    }

    public List<Products> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Optional<Products> getProductById(long id) {
        return this.productRepository.findById(id);
    }

    public Products updateProduct(long id, Products updateProduct) {
        Products existingProduct = this.productRepository.findById(id).get();
        if (existingProduct == null) {
            throw new RuntimeException("Product not found");
        }
        existingProduct.setName(updateProduct.getName());
        existingProduct.setPrice(updateProduct.getPrice());
        existingProduct.setDetailDesc(updateProduct.getDetailDesc());
        existingProduct.setShortDesc(updateProduct.getShortDesc());
        existingProduct.setQuantity(updateProduct.getQuantity());
        existingProduct.setFactory(updateProduct.getFactory());
        existingProduct.setTarget(updateProduct.getTarget());
        return this.productRepository.save(existingProduct);
    }

    public void deleteProductById(long id) {
        this.productRepository.deleteById(id);
    }

    public void handleAddProductToCart(String email, long productId) {
        Users user = this.userService.getUserByEmail(email);
        if (user != null) {
            //check user đã có Cart chưa? nếu chưa -> tao moi
            Cart cart = this.cartRepository.findByUser(user);
            if (cart == null) {
                //tao moi cart
                Cart otherCart = new Cart();
                otherCart.setUser(user);
                otherCart.setSum(1);
                cart = this.cartRepository.save(otherCart);
            }
            //save cart_detail
            Optional<Products> productOptional = this.productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Products realProduct = productOptional.get();
                CartDetail cd = new CartDetail();
                cd.setCart(cart);
                cd.setProducts(realProduct);
                cd.setPrice(realProduct.getPrice());
                cd.setQuantity(1);
                this.cartDetailRepository.save(cd);
            }

        }
    }
}
