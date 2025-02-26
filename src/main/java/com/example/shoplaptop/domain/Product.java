package com.example.shoplaptop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty(message = "Name is required")
    private String name;

    private String image;

    @NotNull
    @DecimalMin(value = "1.0", message = "Price must be greater than 0")
    private double price;

    @NotNull
    @DecimalMin(value = "1", message = "Quantity must be greater than 0")
    private Long quantity;

    @NotNull
    @NotEmpty(message = "Detail description is required")
    @Column(columnDefinition = "MEDIUMTEXT")
    private String detailDesc;

    @NotNull
    @NotEmpty(message = "Short description is required")
    private String shortDesc;

    @NotNull
    @DecimalMin(value = "0", message = "Sold must be greater than 0")
    private Long sold;

    @NotNull
    @NotEmpty(message = "Factory is required")
    private String factory;

    @NotNull
    @NotEmpty(message = "Target is required")
    private String target;

    public Product(Long id, String name, String image, double price, Long quantity, String detailDesc, String shortDesc, Long sold, String factory, String target) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.detailDesc = detailDesc;
        this.shortDesc = shortDesc;
        this.sold = sold;
        this.factory = factory;
        this.target = target;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public Long getSold() {
        return sold;
    }

    public void setSold(Long sold) {
        this.sold = sold;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", image=" + image + ", price=" + price + ", quantity="
                + quantity + ", detailDesc=" + detailDesc + ", shortDesc=" + shortDesc + ", sold=" + sold + ", factory="
                + factory + ", target=" + target + "]";
    }

    
}
