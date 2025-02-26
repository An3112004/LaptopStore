package com.example.shoplaptop.domain.dto;

import com.example.shoplaptop.service.validator.RegisterChecked;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@RegisterChecked
public class RegisterDTO {
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;

    @NotNull(message = "Email is required")
    @Email(message = "Email is not valid" , regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    private String email;

    public RegisterDTO() {
    }

    public RegisterDTO(String firstName, String lastName, String password, String confirmPassword, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
    } 

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    
}
