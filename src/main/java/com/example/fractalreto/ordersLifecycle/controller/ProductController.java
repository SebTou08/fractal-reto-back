package com.example.fractalreto.ordersLifecycle.controller;

import com.example.fractalreto.ordersLifecycle.domain.models.Product;
import com.example.fractalreto.ordersLifecycle.resource.expose.ProductResource;
import com.example.fractalreto.ordersLifecycle.resource.save.SaveProductResource;
import com.example.fractalreto.ordersLifecycle.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/fractal-api")
public class ProductController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProductService productService;

    private Product convertToEntity(SaveProductResource resourse){
        return mapper.map(resourse, Product.class);
    }

    private ProductResource convertToResource(Product entity){
        return mapper.map(entity, ProductResource.class);
    }


    @GetMapping("/products")
    public Page<ProductResource> getAllProducts(Pageable pageable){
        List<ProductResource> products = productService.getAllProducts(pageable)
                .getContent().stream().map(this::convertToResource).toList();
        return new PageImpl<>(products, pageable, products.size());
    }

    @PostMapping("/products")
    public ProductResource createNewProduct (@Valid @RequestBody SaveProductResource resource){
        return convertToResource(productService.createProduct(convertToEntity(resource)));
    }
}
