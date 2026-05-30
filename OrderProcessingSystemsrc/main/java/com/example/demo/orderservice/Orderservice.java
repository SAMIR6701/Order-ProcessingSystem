package com.example.demo.orderservice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.orderEntity.Order;
import com.example.demo.orderEntity.OrderStatus;
import com.example.demo.orderrepository.Repository;

@Service
public class Orderservice implements OrderServiceImpl {

	@Autowired
	private Repository repo;

	// create order number

	@Override
	public Order createOrder(Order order) {
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
	public List<Order> getAllOrders() {

		return repo.findAll();

	}

	@Override
	public Order getOrderbyId(Long id) {

		return repo.findById(id).orElseThrow(() -> new RuntimeException("order Not found with id" + id));
	}

	@Override
	public Order updateOrder(Long id, Order order) {
		Order existingOrder = repo.findById(id)
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
		Order existingOrder = repo.findById(id) .orElseThrow(() -> new RuntimeException("order NOt found with id: " + id));
		repo.delete(existingOrder);

	}

}
