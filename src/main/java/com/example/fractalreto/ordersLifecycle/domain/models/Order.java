package com.example.fractalreto.ordersLifecycle.domain.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.*;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="orders")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private int orderNumber;

    @NotNull
    public String name;

    @NotNull
    public Date date;

    public Status status;

    @NotNull
    private float finalPrice;

    @JsonManagedReference
    @ManyToMany
    private List<Product> products;



}
