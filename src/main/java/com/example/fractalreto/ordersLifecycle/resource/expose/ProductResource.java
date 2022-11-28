package com.example.fractalreto.ordersLifecycle.resource.expose;

import com.example.fractalreto.ordersLifecycle.domain.models.OrderProducts;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResource {
    private int id;
    public String name;
    public float price;

}
