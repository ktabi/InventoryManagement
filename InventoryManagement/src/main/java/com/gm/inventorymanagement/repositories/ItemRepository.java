package com.gm.inventorymanagement.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gm.inventorymanagement.models.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
	
	Optional<Item> findById(Long id);
	
	List<Item> findAll();
	
	void deleteById(Long id);
	
//	Item save(Item i);
	
	
}
