package com.example.laptopshop_project.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name= "products")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String image;
    private String detailDesc;
    private String shortDesc;
    private Long quantity;
    private Long sold;
    private String factory;
    private String target;

    @OneToMany(mappedBy = "products")
    private List<OrderDetail> orderDetails;



}
