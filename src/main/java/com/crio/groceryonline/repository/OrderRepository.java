package com.crio.groceryonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crio.groceryonline.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
    
}
