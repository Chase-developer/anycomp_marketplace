package com.chase.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chase.demo.entities.Item;
import com.chase.demo.entities.Seller;
import com.chase.demo.repositories.ItemRepository;
import com.chase.demo.repositories.SellerRepository;

import jakarta.annotation.Nullable;

@Service
public class SellerService {
	
	@Autowired
    private ItemRepository itemRepository;
	
	@Autowired
    private SellerRepository sellerRepository;
	
	/**
	 * 
	 * @param id
	 * @return item list if seller exists
	 */
	public List<Item> getSellerItems(Long id)
	{
		Optional<Seller> seller = sellerRepository.findById(id);
    	if (seller.isEmpty())
    		return null;
    	return new ArrayList<>(seller.get().getItems().values());
	}
	
	/**
	 * 
	 * @param seller
	 * @return true if did not exist and created
	 */
	public boolean createSeller(Seller seller)
	{
		Optional<Seller> existingBuyer = sellerRepository.findByEmail(seller.getEmail());

        if (existingBuyer.isPresent())
            return false;
        sellerRepository.save(seller);
        return true;
	}

	/**
	 * 
	 * @param id
	 * @return seller if exists
	 */
    public Seller getSeller(Long id) {
        // Get a specific seller
    	Optional<Seller> seller = sellerRepository.findById(id);
    	return seller.isEmpty() ? null : seller.get();
    }
	
	/**
	 * 
	 * @param id
	 * @return true if seller exists and deleted
	 */
	public boolean deleteSeller(Long id) {
        Optional<Seller> seller = sellerRepository.findById(id);
        if (seller.isEmpty()) return false;

        sellerRepository.deleteById(id);
        return true;
    }
	
	/**
	 * 
	 * @param id
	 * @param update
	 * @return true if seller exists and updated
	 */
	public boolean updateSeller(Long id, Seller update)
	{
		Optional<Seller> id_seller = sellerRepository.findById(id);
    	if (id_seller.isEmpty())
    		return false;
    	Seller seller = id_seller.get();
		seller.setName(update.getName());
		seller.setEmail(update.getEmail());
		
		sellerRepository.save(seller);
		return true;
	}
	
	/**
	 * 
	 * @param seller
	 * @param item_name
	 * @return item if name exists
	 */
	public Item getSellerItem(Seller seller, String item_name)
	{
		for (Item item : seller.getItems().values())
		{
			if (item.getName().equals(item_name))
				return item;
		}
		return null;
	}
	
	/**
     * 
     * @param item
     * @return updated Item if exists, else return null
     */
    @Nullable
	public Item addSellerItem(Long id, Item item)
	{
    	Optional<Seller> o_seller = sellerRepository.findById(id);
    	if (o_seller.isEmpty())
    		return null;
    	Seller seller = o_seller.get();
    	
        Item existing = getSellerItem(seller, item.getName());

        if (existing == null) {
            item.setSeller(seller); // Set reverse side of relationship
            itemRepository.save(item);
            return item;
        } else {
            existing.addQty(item.getQuantity());
            return itemRepository.save(existing); // Persist changes
        }
			
	}

}
