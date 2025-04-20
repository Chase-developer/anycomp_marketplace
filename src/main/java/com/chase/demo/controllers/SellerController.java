package com.chase.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chase.demo.entities.Seller;
import com.chase.demo.repositories.SellerRepository;

@RestController
@RequestMapping("/sellers")
public class SellerController {
	
	@Autowired
	private SellerRepository sellerRepository;

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers() {
        // List all sellers
    	return ResponseEntity.ok(sellerRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) {
        // Get a specific seller
    	Optional<Seller> seller = sellerRepository.findById(id);
    	if (seller.isEmpty())
    		return ResponseEntity.notFound().build();
        return ResponseEntity.ok(seller.get());
    }

    @PostMapping
    public ResponseEntity<?> createSeller(@RequestBody Seller seller) {
        // Create a seller
        Optional<Seller> existingBuyer = sellerRepository.findByEmail(seller.getEmail());
        

        if (existingBuyer.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("A buyer with this email already exists.");
        }

        sellerRepository.save(seller);
        return ResponseEntity.status(HttpStatus.CREATED).body(seller);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable Long id, @RequestBody Seller seller) {
        // Update a seller
        
        Optional<Seller> id_buyer = sellerRepository.findById(id);
    	if (id_buyer.isEmpty())
    		return ResponseEntity.notFound().build();
    	id_buyer.get().update(seller);
        return ResponseEntity.ok(id_buyer.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) {
        // Delete a seller
        
        if (sellerRepository.findById(id).isEmpty())
    		return ResponseEntity.notFound().build();
        sellerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
