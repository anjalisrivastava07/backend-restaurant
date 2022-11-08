package org.bct.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bct.springboot.exception.ResourceNotFoundException;
import org.bct.springboot.model.Order;
import org.bct.springboot.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/restaurant")
public class OrderController {

	@Autowired
	private OrderRepository orderRespository;
	
	@PostMapping("/order")
	public Order createOrder(@Validated @RequestBody Order order) {
		return orderRespository.save(order);	
	}
	
	@GetMapping("/order")
	public List<Order> getAllOrder(){
		return orderRespository.findAll();
	}

	@GetMapping("/order/{orderId}")
	public ResponseEntity<Order> getOrderById(@PathVariable(value="orderId") Long orderId) 
			throws ResourceNotFoundException{
		Order order=orderRespository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found fo this id: "
		+orderId));
	return ResponseEntity.ok().body(order);
	}	
	
	@DeleteMapping("/order/{orderId}")
	public Map<String,Boolean> deleteOrder(@PathVariable(value="orderId") Long orderId) 
			throws ResourceNotFoundException{
		Order order=orderRespository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Raw Material Order not found fo this id: "
		+orderId));
		orderRespository.delete(order);
		Map<String, Boolean> response =new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return response;
	}
	
	@PutMapping("/order/{orderId}")
	public ResponseEntity<Order> 
	updateOrder(@PathVariable(value="orderId") Long orderId,@Validated @RequestBody Order orderDetails){
		Order order=orderRespository.findById(orderId)
				.orElseThrow(()-> new ResourceNotFoundException("Raw Material Order not found fo this id: "
						+orderId));
		order.setOrderId(orderDetails.getOrderId());
		order.setOrderName(orderDetails.getOrderName());
		order.setStartTime(orderDetails.getStartTime());
		order.setEndTime(orderDetails.getEndTime());		
		Order updateOrder = orderRespository.save(order);
		return ResponseEntity.ok(updateOrder);
	}	
}