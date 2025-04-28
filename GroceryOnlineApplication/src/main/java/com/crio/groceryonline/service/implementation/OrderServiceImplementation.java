package com.crio.groceryonline.service.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.groceryonline.dto.OrderRequest;
import com.crio.groceryonline.exception.ResourceNotFoundException;
import com.crio.groceryonline.model.Customer;
import com.crio.groceryonline.model.Order;
import com.crio.groceryonline.model.Product;
import com.crio.groceryonline.repository.CustomerRepository;
import com.crio.groceryonline.repository.OrderRepository;
import com.crio.groceryonline.repository.ProductRepository;
import com.crio.groceryonline.service.OrderService;

@Service
public class OrderServiceImplementation implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }

    @Override
    public Order createOrder(OrderRequest orderRequest) {
        Customer customer = customerRepository.findById(orderRequest.getCustomerId()).
        orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + orderRequest.getCustomerId()));

        List<Product> products = productRepository.findAllById(orderRequest.getProductIds());

        if (products.size() != orderRequest.getProductIds().size()) {
            throw new ResourceNotFoundException("One or more products not found");
        }

        Order order = new Order();

        order.setCustomer(customer);
        order.setProducts(products);
        order.setOrderDate(LocalDateTime.now());
        order.calculateTotalPrice();

        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }

}
