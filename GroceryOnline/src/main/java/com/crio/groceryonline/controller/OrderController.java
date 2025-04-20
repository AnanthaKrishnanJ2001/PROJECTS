package com.crio.groceryonline.controller;

import java.util.List;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crio.groceryonline.dto.OrderRequest;
import com.crio.groceryonline.model.Customer;
import com.crio.groceryonline.model.Order;
import com.crio.groceryonline.service.OrderService;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

     @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {

        List<Order> orders = orderService.getAllOrders();

        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public Order createOrder (@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
    
}
