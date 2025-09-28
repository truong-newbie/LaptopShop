package com.example.laptopshop_project.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull
    @Size(min=2, message=" Password phải có tối thiểu 2 ký tự")
    private String password;

    @NotNull
    @Email(message = "Email không hợp lệ", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotNull
    @Size(min=3, message = "Tên phải có tối thiểu 3 ký tự")
    private String fullName;
    private String address;
    private String phone;
    private String avatar;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Roles role;

    @OneToMany( mappedBy = "user")
    List<Orders> orders;



}
