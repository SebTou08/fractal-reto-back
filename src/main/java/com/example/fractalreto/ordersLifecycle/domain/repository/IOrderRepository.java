package com.example.fractalreto.ordersLifecycle.domain.repository;

import com.example.fractalreto.ordersLifecycle.domain.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer> {


}
