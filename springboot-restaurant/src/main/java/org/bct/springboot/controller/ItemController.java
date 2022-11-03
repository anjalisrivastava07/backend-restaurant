package org.bct.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bct.springboot.exception.ResourceNotFoundException;
import org.bct.springboot.model.Item;
import org.bct.springboot.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class ItemController {
	
	@Autowired
	private ItemRepository itemRespository;
	
	@PostMapping("/item")
	public Item createItem(@Validated @RequestBody Item item) {
		return itemRespository.save(item);	
	}
	@GetMapping("/item")
	public List<Item> getAllItem(){
		return itemRespository.findAll();
	}
	@GetMapping("/item/{itemId}")
	public ResponseEntity<Item> getItemById(@PathVariable(value="itemId") Long itemId)
			throws ResourceNotFoundException{
		Item item=itemRespository.findById(itemId)
				.orElseThrow(()-> new ResourceNotFoundException("Item not found for this id: "
						+itemId));
		return ResponseEntity.ok().body(item);
	}
	
	
	@DeleteMapping("/item/{itemId}")
	public Map<String,Boolean> deleteItem(@PathVariable(value="itemId") Long itemId) 
			throws ResourceNotFoundException{
		Item item=itemRespository.findById(itemId)
				.orElseThrow(() -> new ResourceNotFoundException("Item not found fo this id: "
		+itemId));
		itemRespository.delete(item);
		Map<String, Boolean> response =new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return response;
	}
	
	@PutMapping("/item/{itemId}")
	public ResponseEntity<Item> updateOrder(@PathVariable(value="itemId") Long itemId,
			@Validated @RequestBody Item itemDetails){
		Item item = itemRespository.findById(itemId)
				.orElseThrow(()-> new ResourceNotFoundException("Item not found fo this id: "
						+itemId));
		item.setItemId(itemDetails.getItemId());
		item.setItemName(itemDetails.getItemName());
		item.setItemPrice(itemDetails.getItemPrice());
//		item.setOrder(itemDetails.getOrder());
		Item updateItem = itemRespository.save(item);
		return ResponseEntity.ok(updateItem);
	}	
		

}
