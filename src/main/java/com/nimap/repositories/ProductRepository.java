package com.nimap.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nimap.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	Page<Product> findAll(Pageable pageable);

}
