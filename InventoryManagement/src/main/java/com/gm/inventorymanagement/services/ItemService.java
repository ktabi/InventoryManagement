package com.gm.inventorymanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gm.inventorymanagement.models.Item;
import com.gm.inventorymanagement.repositories.ItemRepository;

//Note Service calls on Repositories which is extending CRUD Repositories


@Service
public class ItemService {
	
	private final ItemRepository itemRepo;
	public ItemService(ItemRepository itemRepo) {
		this.itemRepo = itemRepo;
	}
	
	public Item saveItem(Item i) {
		return itemRepo.save(i);
	}
	
	public List<Item> getAllItems() {
		return itemRepo.findAll();
	}
	
	
	public Item findOneItem(Long id) {
//		Optional if there is an instance the item does not exist. 
		Optional<Item> optionalItem = itemRepo.findById(id);
		
		if(optionalItem.isPresent()) {
			return optionalItem.get();
		} else {
			return null;
		}
		
	}
	
	public void deleteItem(Long id) {
		itemRepo.deleteById(id);
	}
	
	
	

}
