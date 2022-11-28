package com.example.fractalreto.ordersLifecycle.domain.valueobs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderProductsId implements Serializable {
    @Column(name = "orderId")
    int orderId;

    @Column(name = "productId")
    int productId;
}
