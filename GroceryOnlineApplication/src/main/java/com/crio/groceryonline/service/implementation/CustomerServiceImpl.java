package com.crio.groceryonline.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.groceryonline.exception.ResourceNotFoundException;
import com.crio.groceryonline.model.Customer;
import com.crio.groceryonline.repository.CustomerRepository;
import com.crio.groceryonline.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Integer id, Customer customerDetails) {
        Customer customer = getCustomerById(id);
        customer.setCustomerName(customerDetails.getCustomerName());
        customer.setCustomerEmail(customerDetails.getCustomerEmail());
        customer.setAddress(customerDetails.getAddress());
        customer.setPhoneNumber(customerDetails.getPhoneNumber());
        customerRepository.save(customer);
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        customerRepository.delete(customer);
    }
    
}
