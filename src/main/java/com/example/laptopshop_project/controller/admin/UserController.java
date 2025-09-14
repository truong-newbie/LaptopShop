package com.example.laptopshop_project.controller.admin;


import com.example.laptopshop_project.domain.Users;
import com.example.laptopshop_project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/admin/user/create")
    public String getUser(Model model,@ModelAttribute("newUser") Users user) {
        //userService.handlesave(user);
        return "admin/user/create";
    }

    @PostMapping("/admin/user/create")
    public String getUser1( Model model,@ModelAttribute("newUser") Users user) {
        userService.handlesave(user);
        return "redirect:/admin/user";
    }
//  GETUSERPAGE

    @GetMapping("/admin/user")
    public String getUserPage(Model model){
        List<Users> users= userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/show";
    }
//

    @GetMapping("/admin/user/update/{id}")
    public String updateUser(Model model,@PathVariable long id, @ModelAttribute("updateUser") Users updateUser){
        Users user= userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/user/update";

    }

    @PostMapping("/admin/user/update/{id}")
    public String updateUser1(@PathVariable long id, @ModelAttribute("updateUser") Users updateUser){
        userService.updateUser(id,updateUser);
        return "redirect:/admin/user";
    }
// GETUSERDETAILPAGE

    @GetMapping("admin/user/{id}")
    public String getUserDetailPage(Model model , @PathVariable long id){
        Users user= userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "admin/user/detail";
    }

 //

//    @GetMapping("admin/user")
//    public String crudUser(){
//        return "admin/user/cruduser";
//    }

    @GetMapping("/admin/user/delete/{id}")
    public String getdeleteUser(@PathVariable long id, Model model){
//        model.addAttribute("id",id);
//        System.out.println("hello");
//        Users user= new Users();
//        user.setId(id);
//        System.out.println("hello");
    Users user = userService.getUserById(id);
        model.addAttribute("newUser", user);
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String deleteUser1(@ModelAttribute("newUser") Users deleteUser){
        System.out.println("check delete1");
        userService.deleteUserById(deleteUser.getId());


        System.out.println("check delete");
        return "redirect:/admin/user";

    }
}
