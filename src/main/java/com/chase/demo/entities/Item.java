package com.chase.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private double price;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    @JsonBackReference("seller-item")
    private Seller seller;
    
    public void removeQty(int qty)
    {
    	this.quantity -= qty;
    }
    
    public void addQty(int qty)
    {
    	this.quantity += qty;
    }
    
}
