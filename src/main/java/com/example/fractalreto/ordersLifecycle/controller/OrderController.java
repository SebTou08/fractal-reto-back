package com.example.fractalreto.ordersLifecycle.controller;

import com.example.fractalreto.ordersLifecycle.domain.models.Order;
import com.example.fractalreto.ordersLifecycle.resource.expose.OrderResource;
import com.example.fractalreto.ordersLifecycle.resource.save.SaveOrderResource;
import com.example.fractalreto.ordersLifecycle.resource.save.SaveProductOrder;
import com.example.fractalreto.ordersLifecycle.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/fractal-api")
public class OrderController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private OrderService orderService;

    private Order convertToEntity(SaveOrderResource resourse){
        return mapper.map(resourse, Order.class);
    }

    private OrderResource convertToResource(Order entity){
        return mapper.map(entity, OrderResource.class);
    }

    @GetMapping("/orders")
    public Page<OrderResource> getAllUsers(Pageable pageable){
        List<OrderResource> users = orderService.getAllOrders(pageable)
                .getContent().stream().map(this::convertToResource).toList();
        return new PageImpl<>(users, pageable, users.size());
    }

    @PostMapping("/orders")
    public OrderResource createNewUser(@Valid @RequestBody SaveOrderResource resourse){
        return convertToResource(orderService.creatOrder(convertToEntity(resourse)));
    }

    @PutMapping("/order/{id}")
    public void assignProducts(@PathVariable(name = "id")int orderId, @RequestBody SaveProductOrder orderDto){
        orderService.assignProducts(orderDto);
    }

}
