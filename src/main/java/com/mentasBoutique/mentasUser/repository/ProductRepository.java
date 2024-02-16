package com.mentasBoutique.mentasUser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mentasBoutique.mentasUser.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{

	
}