package com.chase.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chase.demo.dto.PurchaseDTO;
import com.chase.demo.services.PurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
	
	@Autowired
	private PurchaseService purchaseService;
	
    @PostMapping
    public ResponseEntity<?> createPurchase(@RequestBody PurchaseDTO purchase) {
        // List all sellers
    	return purchaseService.purchaseItem(purchase);
    }
}
