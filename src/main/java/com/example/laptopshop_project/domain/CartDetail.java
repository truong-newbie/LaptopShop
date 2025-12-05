package com.example.laptopshop_project.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long quantity;

    private double price;

    //cart_id:long
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    //product_id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;

}
