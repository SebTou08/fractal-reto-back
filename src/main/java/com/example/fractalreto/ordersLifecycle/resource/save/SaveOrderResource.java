package com.example.fractalreto.ordersLifecycle.resource.save;

import com.example.fractalreto.ordersLifecycle.domain.models.Product;
import com.example.fractalreto.ordersLifecycle.domain.models.Status;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SaveOrderResource {
    @NotNull
    public String name;
    private int orderNumber;

    @NotNull
    public Date date;

    @NotNull
    private float finalPrice;
    public Status status;

    //private List<Product> products;
}
