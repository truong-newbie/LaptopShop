package com.example.laptopshop_project.controller.admin;


import com.example.laptopshop_project.domain.Users;
import com.example.laptopshop_project.service.UploadService;
import com.example.laptopshop_project.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Controller
public class UserController {
    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder PasswordEncoder;

    public UserController(UserService userService ,UploadService uploadService, PasswordEncoder PasswordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.PasswordEncoder=PasswordEncoder;

    }


    @GetMapping("/admin/user/create")
    public String getUser(Model model, @ModelAttribute("newUser") Users user) {
        return "admin/user/create";
    }

    @PostMapping("/admin/user/create")
    public String getUser1(Model model, @ModelAttribute("newUser") @Valid Users user,
                           BindingResult newUserBindingResult,
                           @RequestParam("TruongFile") MultipartFile file) {
        //validate
        if (newUserBindingResult.hasErrors()) {
            // Trả về form để hiển thị lỗi, tránh cho Hibernate ném ConstraintViolationException
            return "admin/user/create";
        }
        String avatar= this.uploadService.handleSaveUploadFile(file,"avatar");
        String hashPassword= this.PasswordEncoder.encode(user.getPassword());
        user.setAvatar(avatar);
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName(user.getRole().getName()));
        userService.handlesave(user);
        return "redirect:/admin/user";
    }
//  GETUSERPAGE

    @GetMapping("/admin/user")
    public String getUserPage(Model model) {
        List<Users> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/show";
    }

    @GetMapping("/admin/user/update/{id}")
    public String updateUser(Model model, @PathVariable long id, @ModelAttribute("updateUser") Users updateUser) {
        Users user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/user/update";

    }

    @PostMapping("/admin/user/update/{id}")
    public String updateUser1(@PathVariable long id, @ModelAttribute("updateUser") Users updateUser) {
        updateUser.setRole(this.userService.getRoleByName(updateUser.getRole().getName()));
        userService.updateUser(id, updateUser);
        return "redirect:/admin/user";
    }
// GETUSERDETAILPAGE

    @GetMapping("admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        Users user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "admin/user/detail";
    }


    @GetMapping("/admin/user/delete/{id}")
    public String getdeleteUser(@PathVariable long id, Model model) {
        Users user = userService.getUserById(id);
        model.addAttribute("newUser", user);
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String deleteUser1(@ModelAttribute("newUser") Users deleteUser) {
        userService.deleteUserById(deleteUser.getId());
        return "redirect:/admin/user";

    }
}
