package com.example.laptopshop_project.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class Users {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    private String password;
    private String email;
    private String fullName;
    private String address;
    private String phone;
    private String avatar;
    private Long roled;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role;

    @OneToMany( mappedBy = "user")
    List<Orders> orders;



}
