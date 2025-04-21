package com.chase.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chase.demo.entities.Item;
import com.chase.demo.repositories.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
    private ItemRepository itemRepository;
	
	

	/**
	 * 
	 * @param id
	 * @return Item if exists
	 */
    public Item getItem(Long id) {
        // Get a specific Item
    	Optional<Item> item = itemRepository.findById(id);
    	return item.isEmpty() ? null : item.get();
    }
	
	/**
	 * 
	 * @param id
	 * @return true if Item exists and deleted
	 */
	public boolean deleteItem(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isEmpty()) return false;

        itemRepository.deleteById(id);
        return true;
    }
	
	/**
	 * 
	 * @param id
	 * @param update
	 * @return true if Item exists and updated
	 */
	public boolean updateItem(Long id, Item update)
	{
		Optional<Item> id_Item = itemRepository.findById(id);
    	if (id_Item.isEmpty())
    		return false;
    	Item item = id_Item.get();
		item.setName(update.getName());
		item.setDescription(update.getDescription());
		item.setPrice(update.getPrice());
		item.setQuantity(update.getQuantity());
		
		itemRepository.save(item);
		return true;
	}

}
