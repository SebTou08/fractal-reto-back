package com.example.fractalreto.ordersLifecycle.service;

import com.example.fractalreto.ordersLifecycle.domain.models.Order;
import com.example.fractalreto.ordersLifecycle.domain.models.OrderProducts;
import com.example.fractalreto.ordersLifecycle.domain.models.Product;
import com.example.fractalreto.ordersLifecycle.domain.repository.IOrderProductRepository;
import com.example.fractalreto.ordersLifecycle.domain.repository.IOrderRepository;
import com.example.fractalreto.ordersLifecycle.domain.repository.IProductRepository;
import com.example.fractalreto.ordersLifecycle.domain.service.IProductOrderService;
import com.example.fractalreto.ordersLifecycle.domain.valueobs.OrderProductsId;
import com.example.fractalreto.ordersLifecycle.resource.expose.ProductsByOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductOrderService implements IProductOrderService {

    @Autowired
    private IOrderProductRepository orderProductRepository;

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IProductRepository productRepository;

    public void unassignProduct(int orderId, int productId){
        OrderProductsId id = new OrderProductsId(orderId,productId);
        OrderProducts existingOrderProduct = orderProductRepository.findById(id).orElseThrow();
        orderProductRepository.delete(existingOrderProduct);
        //NOTE: UPDATE FINAL PRICE
        Order order = orderRepository.findById(orderId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();
        order.setFinalPrice(order.getFinalPrice() - (existingOrderProduct.getQuantity() * product.getPrice()));
        orderRepository.save(order);
    }


    /**
     * @param orderId
     * @param productId
     * @param quantity
     */
    @Override
    public Order addProductsToOrder(int orderId, int productId, int quantity) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();
        OrderProductsId id = new OrderProductsId(orderId,productId);

        Optional<OrderProducts> existingOrderProduct = orderProductRepository.findById(id);
        if(existingOrderProduct.isPresent()){
            existingOrderProduct.get().setQuantity(existingOrderProduct.get().getQuantity()+ quantity);
            orderProductRepository.save(existingOrderProduct.get());
        }
        else {
            OrderProducts orderProducts = new OrderProducts(id, product, order, quantity);
            orderProductRepository.save(orderProducts);
        }
        order.setFinalPrice(calculatePrice(orderId));

        return orderRepository.save(order);
    }

    private float calculatePrice(int orderId){
        List<OrderProducts> orderProductsList = orderProductRepository.listOrderProductsByOrderId(orderId);
        float finalPrice = 0;

        for (OrderProducts orderProducts : orderProductsList) {
            Product product = productRepository.findById(orderProducts.getId().getProductId()).orElseThrow();
            int quantity = orderProducts.getQuantity();
            finalPrice = finalPrice + (quantity * product.getPrice());
        }
        return finalPrice;
    }

    public List<ProductsByOrder> getAllProductsByOrder(int orderId){
        List<ProductsByOrder> productsByOrders = new ArrayList<>();
        List<OrderProducts> orderProductsList = orderProductRepository.listOrderProductsByOrderId(orderId);
        for (OrderProducts orderProducts : orderProductsList) {
            Product product = productRepository.findById(orderProducts.getId().getProductId()).orElseThrow();
            Order order = orderRepository.findById(orderProducts.getId().getOrderId()).orElseThrow();
            ProductsByOrder productsByOrder = new ProductsByOrder(product, orderProducts.getQuantity(), order.getFinalPrice());
            productsByOrders.add(productsByOrder);
        }
        return productsByOrders;
    }

    public OrderProducts updateProductQuantityForExistingOrder(int orderId, int productId, int newQuantity){
        OrderProductsId id = new OrderProductsId(orderId,productId);
        OrderProducts orderProducts = orderProductRepository.findById(id).orElseThrow();
        orderProducts.setQuantity(newQuantity);
        orderProductRepository.save(orderProducts);
        //NOTE: after update the quantity, then update te final price
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setFinalPrice(calculatePrice(orderId));
        orderRepository.save(order);
        return orderProducts;
    }

}
