package com.example.fractalreto.ordersLifecycle.resource.expose;

import com.example.fractalreto.ordersLifecycle.domain.models.Product;
import com.example.fractalreto.ordersLifecycle.domain.models.Status;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderResource {
    private int id;
    public int orderNumber;
    private Date date;
    private float finalPrice;
    public Status status;
    private List<Product> products;
}
