package com.example.laptopshop_project.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Min(value = 0)
    private int sum;

    //user_id
    @OneToOne()
    @JoinColumn(name = "user_id")
    private Users user;

    //cart_detail_id
    @OneToMany(mappedBy = "cart")
    private List<CartDetail> cartDetail;
}
