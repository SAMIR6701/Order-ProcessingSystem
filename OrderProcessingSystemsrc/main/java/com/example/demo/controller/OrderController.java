package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.orderEntity.Order;
import com.example.demo.orderservice.Orderservice;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private Orderservice serv;

	// create order
	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		Order savedOrder = serv.createOrder(order);
		return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);

	}

	// GetAllOrders
	@GetMapping
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> orders = serv.getAllOrders();
		return ResponseEntity.ok(orders);
	}

	// GetByid
	@GetMapping("/{id}")
	public ResponseEntity<Order> getORderByID(@PathVariable Long id) {
		Order order = serv.getOrderbyId(id);
		return ResponseEntity.ok(order);
	}

	// update order
	@PutMapping("/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable Long id,
			@RequestBody Order order) {
		Order updatedorder = serv.updateOrder(id, order);
		return ResponseEntity.ok(updatedorder);
	}

	// Detele order
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable Long id) {

		serv.deleteOrder(id);
		return ResponseEntity.ok("orderDeleted Successfully");

	}
}
