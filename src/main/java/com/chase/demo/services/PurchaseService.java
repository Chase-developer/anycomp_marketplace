package com.chase.demo.services;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chase.demo.dto.PurchaseDTO;
import com.chase.demo.entities.Buyer;
import com.chase.demo.entities.Item;
import com.chase.demo.entities.Purchase;
import com.chase.demo.repositories.BuyerRepository;
import com.chase.demo.repositories.ItemRepository;
import com.chase.demo.repositories.PurchaseRepository;

@Service
public class PurchaseService {
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	@Autowired
    private ItemRepository itemRepository;
	@Autowired
    private BuyerRepository buyerRepository;
	
	public static Purchase createPurchase(Item item, Buyer buyer, int qty)
	{
		Purchase purchase = new Purchase();
	    purchase.setItem(item);
	    purchase.setBuyer(buyer);
	    purchase.setQuantity(qty);
	    purchase.setPurchaseDate(new Timestamp(System.currentTimeMillis()));
	    return purchase;
	}

	public ResponseEntity<?> purchaseItem(PurchaseDTO purchase_dto)
	{
		Optional<Item> o_item = itemRepository.findById(purchase_dto.getItemId());
    	if (o_item.isEmpty())
    		return ResponseEntity.notFound().build();
    	Optional<Buyer> o_buyer = buyerRepository.findById(purchase_dto.getBuyerId());
    	if (o_buyer.isEmpty())
    		return ResponseEntity.notFound().build();
    	
    	Item e_item = o_item.get();
    	if (e_item.getQuantity() < purchase_dto.getQuantity())
    		return ResponseEntity.status(HttpStatus.CONFLICT).body("Insufficient Item Quantity");
    	e_item.removeQty(purchase_dto.getQuantity());
    	if (e_item.getQuantity() == 0)
    		itemRepository.deleteById(e_item.getId());
    	Purchase purchase = createPurchase(e_item, o_buyer.get(), purchase_dto.getQuantity());
    	purchaseRepository.save(purchase);
    	o_buyer.get().addPurchase(purchase);
    	return ResponseEntity.ok(purchase.toDTO());
	}

}
