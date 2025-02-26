package com.example.shoplaptop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    
    @GetMapping("/admin")
    public String admin() {
        return "admin/dashboard/show";
    }

    @GetMapping("/admin/dashboard")
    public String dashboard() {
        return "admin/dashboard/show";
    }


}
