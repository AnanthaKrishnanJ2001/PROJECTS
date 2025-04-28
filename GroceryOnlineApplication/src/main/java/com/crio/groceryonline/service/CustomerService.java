package com.crio.groceryonline.service;

import java.util.List;

import com.crio.groceryonline.model.Customer;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomerById(Integer id);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Integer id, Customer customerDetails);

    void deleteCustomer(Integer id);
    
}
