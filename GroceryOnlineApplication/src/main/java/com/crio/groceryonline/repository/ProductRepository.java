package com.crio.groceryonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crio.groceryonline.model.Product;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Integer>{
    
}
