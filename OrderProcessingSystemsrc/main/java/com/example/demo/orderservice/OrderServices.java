package com.example.demo.orderservice;

import java.util.List;

import com.example.demo.orderEntity.OrderProcessingSystem;

public interface OrderServices {

	// create the order
	OrderProcessingSystem createOrder(OrderProcessingSystem order);

	// GetAllOrders
	List<OrderProcessingSystem> getAllOrders();

	// Get order byid
	OrderProcessingSystem getOrderbyId(Long id);

	// update Order

	OrderProcessingSystem updateOrder(Long id, OrderProcessingSystem order);

	// Delete order
	void deleteOrder(Long id);

}
