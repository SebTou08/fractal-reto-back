package com.example.fractalreto.ordersLifecycle.domain.repository;

import com.example.fractalreto.ordersLifecycle.domain.models.OrderProducts;
import com.example.fractalreto.ordersLifecycle.domain.valueobs.OrderProductsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderProductRepository extends JpaRepository<OrderProducts, OrderProductsId> {
    @Query( "SELECT op FROM OrderProducts op WHERE op.id.orderId = ?1")
    public List<OrderProducts> listOrderProductsByOrderId(int orderId);
}
