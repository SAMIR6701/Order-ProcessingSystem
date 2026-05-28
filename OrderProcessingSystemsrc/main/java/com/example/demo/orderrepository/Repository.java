package com.example.demo.orderrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.orderEntity.OrderProcessingSystem;

public interface Repository extends JpaRepository<OrderProcessingSystem,Long> {

}
