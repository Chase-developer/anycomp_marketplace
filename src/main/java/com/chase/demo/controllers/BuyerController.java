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

import com.chase.demo.entities.Buyer;
import com.chase.demo.repositories.BuyerRepository;

@RestController
@RequestMapping("/buyers")
public class BuyerController {
	
	@Autowired
	private BuyerRepository buyerRepository;

    @GetMapping
    public ResponseEntity<List<Buyer>> getAllBuyers() {
        // List all buyers
        return ResponseEntity.ok(buyerRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Buyer> getBuyerById(@PathVariable Long id) {
        // Get a specific buyer
    	Optional<Buyer> buyer = buyerRepository.findById(id);
    	if (buyer.isEmpty())
    		return ResponseEntity.notFound().build();
        return ResponseEntity.ok(buyer.get());
    }

    @PostMapping
    public ResponseEntity<?> createBuyer(@RequestBody Buyer buyer) {
        // Create a buyer
    	Optional<Buyer> existingBuyer = buyerRepository.findById(buyer.getId());

        if (existingBuyer.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("A buyer with this email already exists.");
        }

        buyerRepository.save(buyer);
        return ResponseEntity.status(HttpStatus.CREATED).body(buyer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Buyer> updateBuyer(@PathVariable Long id, @RequestBody Buyer buyer) {
    	Optional<Buyer> id_buyer = buyerRepository.findById(id);
    	if (id_buyer.isEmpty())
    		return ResponseEntity.notFound().build();
    	id_buyer.get().update(buyer);
        return ResponseEntity.ok(id_buyer.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuyer(@PathVariable Long id) {
        // Delete a buyer
    	if (buyerRepository.findById(id).isEmpty())
    		return ResponseEntity.notFound().build();
    	buyerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
