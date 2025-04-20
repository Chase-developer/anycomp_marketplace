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
import org.springframework.web.bind.annotation.RestController;

import com.chase.demo.entities.Item;
import com.chase.demo.entities.Seller;
import com.chase.demo.repositories.ItemRepository;
import com.chase.demo.repositories.SellerRepository;

@RestController
public class ItemController {
	
	@Autowired
    private ItemRepository itemRepository;
	
	@Autowired
    private SellerRepository sellerRepository;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        // List all items
        return ResponseEntity.ok(itemRepository.findAll());
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        // Get item by ID
    	Optional<Item> item = itemRepository.findById(id);
    	if (item.isEmpty())
    		return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(item.get());
    }

    @GetMapping("/sellers/{sellerId}/items")
    public ResponseEntity<List<Item>> getItemsBySeller(@PathVariable Long sellerId) {
        // Get items by seller
    	Optional<Seller> seller = sellerRepository.findById(sellerId);
    	if (seller.isEmpty())
    		return ResponseEntity.notFound().build();
        return ResponseEntity.ok(seller.get().getItems());
    }

    @PostMapping("/sellers/{sellerId}/items")
    public ResponseEntity<Item> addItemToSeller(@PathVariable Long sellerId, @RequestBody Item item) {
        // Add new item to seller
    	Optional<Seller> seller = sellerRepository.findById(sellerId);
    	if (seller.isEmpty())
    		return ResponseEntity.notFound().build();
    	seller.get().addItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
        // Update item
    	Optional<Item> id_item = itemRepository.findById(id);
    	if (id_item.isEmpty())
    		return ResponseEntity.notFound().build();
    	id_item.get().update(item);
        return ResponseEntity.ok(id_item.get());
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        // Delete item
    	if (itemRepository.findById(id).isEmpty())
    		return ResponseEntity.notFound().build();
    	itemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
