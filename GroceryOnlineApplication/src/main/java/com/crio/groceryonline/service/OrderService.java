package com.crio.groceryonline.service;

import com.crio.groceryonline.dto.OrderRequest;
import com.crio.groceryonline.model.Order;

public interface OrderService {

    java.util.List<Order> getAllOrders();

    Order getOrderById(Integer id);

    Order createOrder(OrderRequest orderRequest);

    void deleteOrder(Integer id);
    
}
