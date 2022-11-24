package com.example.fractalreto.ordersLifecycle.service;

import com.example.fractalreto.ordersLifecycle.domain.models.Order;
import com.example.fractalreto.ordersLifecycle.domain.models.Product;
import com.example.fractalreto.ordersLifecycle.domain.models.Status;
import com.example.fractalreto.ordersLifecycle.domain.repository.IOrderRepository;
import com.example.fractalreto.ordersLifecycle.domain.repository.IProductRepository;
import com.example.fractalreto.ordersLifecycle.domain.service.IOrderService;
import com.example.fractalreto.ordersLifecycle.exception.ResourceNotFoundException;
import com.example.fractalreto.ordersLifecycle.resource.save.SaveProductOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IProductRepository productRepository;



    /**
     * @param orderId
     */
    @Override
    public ResponseEntity<?> deleteOrder(int orderId) {
        Order foundOrder = orderRepository.findById(orderId)
                .orElseThrow(ResourceNotFoundException::new);
        orderRepository.delete(foundOrder);
        return ResponseEntity.ok().build();
    }

    /**
     * @param order
     * @return
     */
    @Override
    public Order creatOrder(Order order) {
        if (order.getStatus() == null){
            order.setStatus(Status.Pending);
        }
        return orderRepository.save(order);
    }

    /**
     * @param orderId
     * @param order
     * @return
     */
    @Override
    public Order updateOrder(int orderId, Order order) {
        Order foundOrder = orderRepository.findById(orderId)
                .orElseThrow(ResourceNotFoundException::new);
        foundOrder = order;
        return orderRepository.save(foundOrder);
    }

    /**
     * @param pageable
     * @return
     */
    @Override
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public void assignProducts(SaveProductOrder dto){
        List<Product> products = new ArrayList<>();
        Order order = orderRepository.findById(dto.getOrderId()).orElseThrow();
        for(int i = 0; i<dto.getProductsIds().size(); i++){
            Product product = productRepository.findById(dto.getProductsIds().get(i)).orElseThrow();
            products.add(product);
        }

        order.setProducts(products);
        System.out.println(order);
        orderRepository.save(order);
    }
}
