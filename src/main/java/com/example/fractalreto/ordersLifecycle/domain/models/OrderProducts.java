package com.example.fractalreto.ordersLifecycle.domain.models;

import com.example.fractalreto.ordersLifecycle.domain.valueobs.OrderProductsId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name ="order_products")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderProducts {
    @EmbeddedId
    private OrderProductsId id;

    @JsonIgnore
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    Order order;


    private int quantity;
}
