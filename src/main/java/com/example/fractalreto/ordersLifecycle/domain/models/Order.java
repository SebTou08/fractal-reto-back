package com.example.fractalreto.ordersLifecycle.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.*;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="orders")
@Setter
@Getter
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private int orderNumber;


    @NotNull
    public Date date;

    public Status status;

    private float finalPrice;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderProducts> orderProducts;

    public Order() {
        this.finalPrice = 0;
    }

    public Order(int id, int orderNumber, Date date, Status status, List<OrderProducts> orderProducts) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.date = date;
        this.status = status;
        this.finalPrice = 0;
        this.orderProducts = orderProducts;
    }
}
