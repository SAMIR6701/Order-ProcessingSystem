package com.example.demo.orderservice;

import java.util.List;

import com.example.demo.orderEntity.Order;

public interface OrderServiceImpl {

	// create the order
	Order createOrder(Order order);

	// GetAllOrders
	List<Order> getAllOrders();

	// Get order byid
	Order getOrderbyId(Long id);

	// update Order

	Order updateOrder(Long id, Order order);

	// Delete order
	void deleteOrder(Long id);

}
