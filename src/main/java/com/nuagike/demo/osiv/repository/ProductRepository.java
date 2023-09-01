package com.nuagike.demo.osiv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuagike.demo.osiv.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<Product> findOneByName(String name);
}
