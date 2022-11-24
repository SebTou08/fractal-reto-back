package com.example.fractalreto.ordersLifecycle.resource.save;

import lombok.Getter;

import java.util.List;

@Getter
public class SaveProductOrder {
    private int orderId;
    private List<Integer> productsIds;
}
