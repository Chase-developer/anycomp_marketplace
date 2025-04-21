package com.chase.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chase.demo.entities.Item;
import com.chase.demo.repositories.ItemRepository;
import com.chase.demo.services.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {
	
	@Autowired
    private ItemRepository itemRepository;
	
	@Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems(Pageable pageable) {
        // List all items
        return ResponseEntity.ok(itemRepository.findAll(pageable).getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") Long id) {
        // Get item by ID
    	Item item = itemService.getItem(id);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }

    

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable("id") Long id, @RequestBody Item item) {
    	return itemService.updateItem(id, item) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") Long id) {
        // Delete item
    	return itemService.deleteItem(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
