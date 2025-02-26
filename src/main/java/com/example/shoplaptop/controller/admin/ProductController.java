package com.example.shoplaptop.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.classfile.ClassFile.Option;
import java.util.List;
import java.util.Optional;

import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.shoplaptop.domain.Product;
import com.example.shoplaptop.domain.Role;
import com.example.shoplaptop.domain.User;
import com.example.shoplaptop.service.ProductService;
import com.example.shoplaptop.service.UpLoadService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;

@Controller
public class ProductController {

    private final ProductService productService;
    private final UpLoadService upLoadService;

    public ProductController(
                            ProductService productService,
                            UpLoadService upLoadService) {
        this.productService = productService;
        this.upLoadService = upLoadService;
    }
    
    @GetMapping("/admin/product")
    public String index(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String createProduct(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/add")
    public String addProduct(
                            Model model,
                            @ModelAttribute("newProduct") @Valid Product product,
                            BindingResult editProductBindingResult,
                            @RequestParam("imageFile") MultipartFile file) {

        List<FieldError> errors = editProductBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>> " + error.getField() + " - " + error.getDefaultMessage());
        }

        if (editProductBindingResult.hasErrors()) {
            return "admin/product/create";
        }

        String fileNameImg = this.upLoadService.saveUpLoadFile(file);
        System.out.println(fileNameImg);
        product.setImage(fileNameImg);
        this.productService.save(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/edit/{id}")
    public String editProduct(Model model, @PathVariable("id") Long id) {
        Optional<Product> product = this.productService.findById(id);
        model.addAttribute("editProduct", product.get());
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String updateProduct(
                            Model model,
                            @ModelAttribute("editProduct") @Valid Product product,
                            BindingResult editProductBindingResult,
                            @RequestParam("imageFile") MultipartFile file) {

        List<FieldError> errors = editProductBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>> " + error.getField() + " - " + error.getDefaultMessage());
        }

        if (editProductBindingResult.hasErrors()) {
            return "admin/product/update";
        }
        Optional<Product> productOld = this.productService.findById(product.getId());
        String fileNameImg = this.upLoadService.saveUpLoadFile(file);
        product.setImage(fileNameImg);
        if(productOld.isPresent()) {
            Product existingProduct= productOld.get();
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setDetailDesc(product.getDetailDesc());
            existingProduct.setShortDesc(product.getShortDesc());
            existingProduct.setSold(product.getSold());
            existingProduct.setFactory(product.getFactory());
            existingProduct.setTarget(product.getTarget());
            existingProduct.setImage(product.getImage());
            this.productService.save(existingProduct);
        } else {
            model.addAttribute("error", "User not found");
        }
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String delete(Model model , @PathVariable("id") Long id) {
        Optional<Product> product = this.productService.findById(id);
        model.addAttribute("deleteProduct", product.get());
        return "admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String deleteProduct(Model model , @ModelAttribute("deleteProduct") Product product) {
        Optional<Product> productOld = this.productService.findById(product.getId());
        if(productOld.isPresent()) {
            this.productService.deleteById(product.getId());
        } else {
            model.addAttribute("error", "User not found");
        }
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Optional<Product> product = this.productService.findById(id);
        model.addAttribute("id", id);
        model.addAttribute("product", product.get());
        return "admin/product/detail";
    }
}
