package com.example.fractalreto.ordersLifecycle.domain.service;

import com.example.fractalreto.ordersLifecycle.domain.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Product createProduct(Product product);
    Product updateProduct(int productId, Product product);
    Page<Product> getAllProducts(Pageable pageable);

}
