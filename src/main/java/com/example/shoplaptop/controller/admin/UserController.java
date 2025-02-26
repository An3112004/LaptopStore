package com.example.shoplaptop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.shoplaptop.domain.Role;
import com.example.shoplaptop.domain.User;
import com.example.shoplaptop.service.RoleService;
import com.example.shoplaptop.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;

    public UserController(PasswordEncoder passwordEncoder , UserService userService , RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService = roleService;
    }
    
    @GetMapping("/admin/user") 
    public String index(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/show";
    }

    @GetMapping("/admin/user/create")
    public String create(Model model) {
        model.addAttribute("newUser", new User());
        List<Role> roles = this.roleService.findAll();
        model.addAttribute("roles", roles);
        return "admin/user/create";
    }

    @PostMapping("/admin/user/add")
    public String addUser(Model model , @ModelAttribute("newUser") @Valid User user , BindingResult newUserBindingResult) {
        List<Role> roles = this.roleService.findAll();
        model.addAttribute("roles", roles);
        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>> " + error.getField() + " - " + error.getDefaultMessage());
        }

        if (newUserBindingResult.hasErrors()) {
            return "admin/user/create";
        }
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        this.userService.saveUser(user);
        return "redirect:/admin/user";
    }
    

    @GetMapping("/admin/user/edit/{id}")
    public String edit(Model model , @PathVariable("id") Long id) {
        Optional<User> user = this.userService.getUserById(id);
        List<Role> roles = this.roleService.findAll();
        model.addAttribute("roles", roles);
        if (user.isPresent()) {
            model.addAttribute("editUser", user.get());
            model.addAttribute("roles", roles);
        } else {
            model.addAttribute("error", "User not found");
        }
        return "admin/user/update";
    }

    @PostMapping("/admin/user/update")
    public String updateUser(Model model , @ModelAttribute("editUser") @Valid User user , BindingResult editUserBindingResult) {
        List<FieldError> errors = editUserBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>> " + error.getField() + " - " + error.getDefaultMessage());
        }

        if (editUserBindingResult.hasErrors()) {
            List<Role> roles = this.roleService.findAll();
            model.addAttribute("roles", roles); 
            return "admin/user/update";
        }

        Optional<User> crrUser = this.userService.getUserById(user.getId());
        if(crrUser.isPresent()) {
            User existingUser = crrUser.get();
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setAddress(user.getAddress());
            existingUser.setPhone(user.getPhone());
            this.userService.saveUser(existingUser);
        } else {
            model.addAttribute("error", "User not found");
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String delete(Model model , @PathVariable("id") Long id) {
        Optional<User> user = this.userService.getUserById(id);
        if (user.isPresent()) {
            model.addAttribute("deleteUser", user.get());
        } else {
            model.addAttribute("error", "User not found");
        }
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/remove")
    public String postDeleteUser(Model model, @ModelAttribute("deleteUser") User user) {
        this.userService.deleteUser(user.getId());
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/{id}")
    public String detail(Model model , @PathVariable("id") Long id) {
        Optional<User> user = this.userService.getUserById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
        } else {
            model.addAttribute("error", "User not found");
        }
        return "admin/user/detail";
    }
}
