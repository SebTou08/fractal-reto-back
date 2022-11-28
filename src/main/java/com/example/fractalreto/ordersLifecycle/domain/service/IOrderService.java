package com.example.fractalreto.ordersLifecycle.domain.service;

import com.example.fractalreto.ordersLifecycle.domain.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface IOrderService {
    ResponseEntity<?> deleteOrder(int orderId);
    Order creatOrder(Order order);
    Order updateOrder(int orderId, Order order);
    List<Order> getAllOrders( );

}
