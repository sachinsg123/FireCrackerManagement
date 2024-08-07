package com.fcmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fcmanagement.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	

}
