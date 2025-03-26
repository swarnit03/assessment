package com.assessment.backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;

    // Explicit Getter and Setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { // Ensure this method exists
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() { // Ensure this method exists
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

