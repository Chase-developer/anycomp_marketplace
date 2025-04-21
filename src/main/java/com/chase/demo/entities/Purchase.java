package com.chase.demo.entities;


import java.sql.Timestamp;

import com.chase.demo.dto.PurchaseDTO;
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
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    @JsonBackReference("buyer-purchase")
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonBackReference("item-purchase")
    private Item item;

    private Integer quantity;
    private Timestamp purchaseDate;
    
    public PurchaseDTO toDTO()
    {
    	PurchaseDTO purchase_dto = new PurchaseDTO();
		purchase_dto.setItemId(item.getId());
		purchase_dto.setBuyerId(buyer.getId());
		purchase_dto.setQuantity(quantity);
		purchase_dto.setPurchaseDate(purchaseDate);
	    return purchase_dto;
    }

}
