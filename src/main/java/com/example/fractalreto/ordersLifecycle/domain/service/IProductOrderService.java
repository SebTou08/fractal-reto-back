package com.example.fractalreto.ordersLifecycle.domain.service;

import com.example.fractalreto.ordersLifecycle.domain.models.Order;

public interface IProductOrderService {
    Order addProductsToOrder(int orderId, int productId, int quantity);
}
