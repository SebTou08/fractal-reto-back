package com.example.fractalreto.ordersLifecycle.resource.expose;

import com.example.fractalreto.ordersLifecycle.domain.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductsByOrder {
    private Product product;
    private int quantity;
    private float totalPrice;
}
