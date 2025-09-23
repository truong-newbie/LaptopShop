package com.example.laptopshop_project.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private long id;
    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Price phải lớn hơn 0")
    private double price;
    private String image;
    @NotBlank(message = "DetailDesc không được để trống")
    @Column(columnDefinition = "MEDIUMTEXT")
    private String detailDesc;
    @NotBlank(message = "ShortDesc không được để trống")
    private String shortDesc;
    @NotNull
    @Min(value=1, message="Số lượng cần lớn hơn hoặc bằng 1")
    private long quantity;

    private long sold;
    private String factory;
    private String target;

    @OneToMany(mappedBy = "products")
    private List<OrderDetail> orderDetails;



}
