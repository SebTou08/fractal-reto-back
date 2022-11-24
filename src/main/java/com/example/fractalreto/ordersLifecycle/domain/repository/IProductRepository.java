package com.example.fractalreto.ordersLifecycle.domain.repository;

import com.example.fractalreto.ordersLifecycle.domain.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {
}
