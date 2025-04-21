package com.chase.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chase.demo.dto.PurchaseDTO;
import com.chase.demo.entities.Buyer;
import com.chase.demo.repositories.BuyerRepository;
import com.chase.demo.services.BuyerService;

@RestController
@RequestMapping("/buyers")
public class BuyerController {
	
	@Autowired
	private BuyerRepository buyerRepository;
	
	@Autowired
	private BuyerService buyerService;

    @GetMapping
    public ResponseEntity<List<Buyer>> getAllBuyers(Pageable pageable) {
        // List all buyers
        return ResponseEntity.ok(buyerRepository.findAll(pageable).getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Buyer> getBuyerById(@PathVariable("id") Long id) {
        // Get a specific buyer
    	Buyer buyer = buyerService.getBuyer(id);
        return buyer != null ? ResponseEntity.ok(buyer) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createBuyer(@RequestBody Buyer buyer) {
        // Create a buyer
    	return buyerService.createBuyer(buyer) ? ResponseEntity.status(HttpStatus.CREATED).body(buyer) :
        	ResponseEntity.status(HttpStatus.CONFLICT).body("A buyer with this email already exists.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBuyer(@PathVariable("id") Long id, @RequestBody Buyer buyer) {
    	return buyerService.updateBuyer(id, buyer) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuyer(@PathVariable("id") Long id) {
        // Delete a buyer
    	return buyerService.deleteBuyer(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/{id}/purchases")
    public ResponseEntity<List<PurchaseDTO>> getBuyerPurchases(@PathVariable("id") Long id, 
    		@RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        // Get Buyer Purchases
    	List<PurchaseDTO> purchases = buyerService.getPurchases(id, size, page);
    	return purchases != null ? ResponseEntity.ok(purchases) : ResponseEntity.notFound().build();
    }
}
