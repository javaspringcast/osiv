package com.nuagike.demo.osiv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuagike.demo.osiv.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	
}
