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
import org.springframework.web.bind.annotation.RestController;

import com.chase.demo.entities.Item;
import com.chase.demo.entities.Seller;
import com.chase.demo.repositories.SellerRepository;
import com.chase.demo.services.SellerService;

@RestController
@RequestMapping("/sellers")
public class SellerController {
	
	@Autowired
	private SellerRepository sellerRepository;
	
	@Autowired
	private SellerService sellerService;

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers(Pageable pageable) {
        // List all sellers
    	return ResponseEntity.ok(sellerRepository.findAll(pageable).getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable("id") Long id) {
        // Get a specific seller
    	Seller seller = sellerService.getSeller(id);
        return seller == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(seller);
    }

    @PostMapping
    public ResponseEntity<?> createSeller(@RequestBody Seller seller) {
        // Create a seller
        return sellerService.createSeller(seller) ? ResponseEntity.status(HttpStatus.CREATED).body(seller) :
        	ResponseEntity.status(HttpStatus.CONFLICT).body("A buyer with this email already exists.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSeller(@PathVariable("id") Long id, @RequestBody Seller seller) {
        // Update a seller
        return sellerService.updateSeller(id, seller) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable("id") Long id) {
        // Delete a seller
        return sellerService.deleteSeller(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/{id}/items")
    public ResponseEntity<List<Item>> getItemsBySeller(@PathVariable("id") Long sellerId) {
        // Get items by seller
    	List<Item> items = sellerService.getSellerItems(sellerId);
        return ResponseEntity.ok(items);
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<?> addItemToSeller(@PathVariable("id") Long sellerId, @RequestBody Item item) {
        // Add new item to seller
    	Item e_item = sellerService.addSellerItem(sellerId, item);
        return e_item == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seller Does Not Exist") 
        		: ResponseEntity.status(HttpStatus.CREATED).body(e_item);
    }
}
