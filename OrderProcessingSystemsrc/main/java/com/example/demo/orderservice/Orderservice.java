package com.example.demo.orderservice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.orderEntity.OrderProcessingSystem;
import com.example.demo.orderEntity.OrderStatus;
import com.example.demo.orderrepository.Repository;

@Service
public class Orderservice implements OrderServices {

	@Autowired
	private Repository repo;

	// create order number

	@Override
	public OrderProcessingSystem createOrder(OrderProcessingSystem order) {
		// Generate unique order number

		order.setOrdernumber(UUID.randomUUID().toString());

		// calculate the total amount

		Double total_amount = order.getPrice() * order.getQuantity();

		order.setTotal_amount(total_amount);

		// Set Order status
		order.setStatus(OrderStatus.CREATED);

		// set created Date time
		order.setCreateAt(LocalDateTime.now());

		// save into database
		return repo.save(order);

	}

	@Override
	public List<OrderProcessingSystem> getAllOrders() {

		return repo.findAll();

	}

	@Override
	public OrderProcessingSystem getOrderbyId(Long id) {

		return repo.findById(id).orElseThrow(() -> new RuntimeException("order Not found with id" + id));
	}

	@Override
	public OrderProcessingSystem updateOrder(Long id, OrderProcessingSystem order) {
		OrderProcessingSystem existingOrder = repo.findById(id)
				.orElseThrow(() -> new RuntimeException("orderNotfound with id" + id));

		existingOrder.setCustomername(order.getCustomername());
		existingOrder.setProductName(order.getProductName());
		existingOrder.setQuantity(order.getQuantity());
		existingOrder.setPrice(order.getPrice());

		// Recalculate the total amount
		Double total_amount = order.getPrice() * order.getQuantity();

		existingOrder.setTotal_amount(total_amount);

		// update status
		existingOrder.setStatus(order.getStatus());

		return repo.save(existingOrder);
	}

	@Override
	public void deleteOrder(Long id) {
		OrderProcessingSystem existingOrder = repo.findById(id)
				.orElseThrow(() -> new RuntimeException("order NOt found with id: " + id));
		repo.delete(existingOrder);

	}

}
