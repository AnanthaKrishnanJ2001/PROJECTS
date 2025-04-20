package com.crio.groceryonline.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crio.groceryonline.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    Customer findBycustomerEmail(String customerEmail);
}
