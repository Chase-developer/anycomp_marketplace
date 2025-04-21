package com.chase.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chase.demo.dto.PurchaseDTO;
import com.chase.demo.entities.Buyer;
import com.chase.demo.entities.Purchase;
import com.chase.demo.repositories.BuyerRepository;

@Service
public class BuyerService {
	
	@Autowired
    private BuyerRepository buyerRepository;
	
	/**
	 * 
	 * @param id
	 * @return item list if Buyer exists
	 */
	public List<PurchaseDTO> getPurchases(Long id, int size, int page)
	{
		Optional<Buyer> buyer = buyerRepository.findById(id);
    	if (buyer.isEmpty())
    		return null;
    	List<PurchaseDTO> purchase_dtos = new ArrayList<PurchaseDTO>();
    	List<Purchase> buyer_purchases = buyer.get().getPurchasedItems();
    	int start = Math.min(page * size, buyer_purchases.size());
        int end = Math.min(start + size, buyer_purchases.size());
    	List<Purchase> purchases = buyer_purchases.subList(start, end);
    	
    	for (Purchase purchase : purchases)
    		purchase_dtos.add(purchase.toDTO());
    	return purchase_dtos;
	}
	
	/**
	 * 
	 * @param Buyer
	 * @return true if did not exist and created
	 */
	public boolean createBuyer(Buyer Buyer)
	{
		Optional<Buyer> existingBuyer = buyerRepository.findByEmail(Buyer.getEmail());

        if (existingBuyer.isPresent())
            return false;
        buyerRepository.save(Buyer);
        return true;
	}

	/**
	 * 
	 * @param id
	 * @return Buyer if exists
	 */
    public Buyer getBuyer(Long id) {
        // Get a specific Buyer
    	Optional<Buyer> buyer = buyerRepository.findById(id);
    	return buyer.isEmpty() ? null : buyer.get();
    }
	
	/**
	 * 
	 * @param id
	 * @return true if Buyer exists and deleted
	 */
	public boolean deleteBuyer(Long id) {
        Optional<Buyer> buyer = buyerRepository.findById(id);
        if (buyer.isEmpty()) return false;

        buyerRepository.deleteById(id);
        return true;
    }
	
	/**
	 * 
	 * @param id
	 * @param update
	 * @return true if Buyer exists and updated
	 */
	public boolean updateBuyer(Long id, Buyer update)
	{
		Optional<Buyer> id_Buyer = buyerRepository.findById(id);
    	if (id_Buyer.isEmpty())
    		return false;
    	Buyer buyer = id_Buyer.get();
		buyer.setName(update.getName());
		buyer.setEmail(update.getEmail());
		
		buyerRepository.save(buyer);
		return true;
	}

}
