package com.example.laptopshop_project.service;

import com.example.laptopshop_project.domain.*;
import com.example.laptopshop_project.repository.*;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
                          CartDetailRepository cartDetailRepository,
                          UserService userService, OrderRepository orderRepository,
                          OrderDetailRepository orderDetailRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
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

    public void handleAddProductToCart(String email, long productId, HttpSession session) {
        Users user = this.userService.getUserByEmail(email);
        if (user != null) {
            //check user đã có Cart chưa? nếu chưa -> tao moi
            Cart cart = this.cartRepository.findByUser(user);
            if (cart == null) {
                //tao moi cart
                Cart otherCart = new Cart();
                otherCart.setUser(user);
                otherCart.setSum(0);
                cart = this.cartRepository.save(otherCart);
            }
            //save cart_detail
            Optional<Products> productOptional = this.productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Products realProduct = productOptional.get();

                CartDetail oldDetail = this.cartDetailRepository.findByCartAndProducts(cart, realProduct);
                if (oldDetail == null) {
                    CartDetail cd = new CartDetail();
                    cd.setCart(cart);
                    cd.setProducts(realProduct);
                    cd.setPrice(realProduct.getPrice());
                    cd.setQuantity(1);
                    this.cartDetailRepository.save(cd);

                    //update cart(sum)
                    int s = cart.getSum() + 1;
                    cart.setSum(s);
                    this.cartRepository.save(cart);
                    session.setAttribute("sum", s);
                } else {
                    oldDetail.setQuantity(oldDetail.getQuantity() + 1);
                    this.cartDetailRepository.save(oldDetail);
                }


            }

        }
    }

    public Cart fetchByUser(Users user) {
        return this.cartRepository.findByUser(user);
    }

    @Transactional
    public void handleRemoveCartDetail(long cartDetailId, HttpSession session) {
        Optional<CartDetail> cartDetailOptional = this.cartDetailRepository.findById(cartDetailId);
        if (cartDetailOptional.isPresent()) {
            CartDetail cartDetail = cartDetailOptional.get();
            Cart currentCart = cartDetail.getCart();
            //delete cart-detail
            this.cartDetailRepository.deleteById(cartDetailId);

            //update cart
            if (currentCart.getSum() > 1) {
                //update current cart
                int s = currentCart.getSum() - 1;
                currentCart.setSum(s);
                session.setAttribute("sum", s);
                this.cartRepository.save(currentCart);
            } else {
                //delete cart(sum =1)
                this.cartRepository.deleteById(currentCart.getId());
                session.setAttribute("sum", 0);
            }
        }
    }

    @Transactional
    public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails) {
        for (CartDetail cartDetail : cartDetails) {
            Optional<CartDetail> cdOptional = this.cartDetailRepository.findById(cartDetail.getId());
            if (cdOptional.isPresent()) {
                CartDetail currentCartDetail = cdOptional.get();
                currentCartDetail.setQuantity(cartDetail.getQuantity());
                this.cartDetailRepository.save(currentCartDetail);
            }
        }
    }

    public void handlePlaceOrder(Users user, HttpSession session,
                                 String receiverName, String receiverAddress, String receiverPhone) {
        //create new order
//        Orders order = new Orders();
//        order.setUser(user);
//        order.setReceiverName(receiverName);
//        order.setReceiverAddress(receiverAddress);
//        order.setReceiverPhone(receiverPhone);
//        order = this.orderRepository.save(order);
//        System.out.println("1");
        //create orderDetail

        //step 1:
        Cart cart = this.cartRepository.findByUser(user);
        if (cart != null) {
            List<CartDetail> cartDetails = cart.getCartDetails();

            if (cartDetails != null) {
                Orders order = new Orders();
                order.setUser(user);
                order.setReceiverName(receiverName);
                order.setReceiverAddress(receiverAddress);
                order.setReceiverPhone(receiverPhone);
                order.setStatus("PENDING");

                double sum = 0;
                for (CartDetail cd : cartDetails) {
                    sum += cd.getPrice();
                }
                order.setTotalPrice(sum);
                order = this.orderRepository.save(order);

                //create orderdetail
                for (CartDetail cd : cartDetails) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrders(order);
                    orderDetail.setProducts(cd.getProducts());
                    orderDetail.setPrice(cd.getPrice());
                    orderDetail.setQuantity(cd.getQuantity());
                    this.orderDetailRepository.save(orderDetail);
                }
              
                //step 2: delete cart_detail and cart

                for (CartDetail cd : cartDetails) {
                    this.cartDetailRepository.deleteById(cd.getId());
                }
                this.cartRepository.deleteById(cart.getId());
                System.out.println("3");
                //step 3 :update session
                session.setAttribute("sum", 0);
                System.out.println("4");
            }
        }


    }


}
