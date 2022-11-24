package com.example.fractalreto.ordersLifecycle.resource.save;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveProductResource {
    @NotNull
    public String name;

    @NotNull
    public float price;
}
