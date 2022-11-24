package com.example.fractalreto.ordersLifecycle.service;

import com.example.fractalreto.ordersLifecycle.domain.models.Product;
import com.example.fractalreto.ordersLifecycle.domain.repository.IProductRepository;
import com.example.fractalreto.ordersLifecycle.domain.service.IProductService;
import com.example.fractalreto.ordersLifecycle.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    /**
     * @param product
     * @return
     */
    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * @param productId
     * @param product
     * @return
     */
    @Override
    public Product updateProduct(int productId, Product product) {
        Product productFound = productRepository.findById(productId)
                .orElseThrow(ResourceNotFoundException::new);
        productFound = product;
        return productRepository.save(productFound);
    }

    /**
     * @param pageable
     * @return
     */
    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
