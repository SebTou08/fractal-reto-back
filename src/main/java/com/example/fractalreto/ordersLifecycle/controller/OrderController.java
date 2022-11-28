package com.example.fractalreto.ordersLifecycle.controller;

import com.example.fractalreto.ordersLifecycle.domain.models.Order;
import com.example.fractalreto.ordersLifecycle.domain.models.OrderProducts;
import com.example.fractalreto.ordersLifecycle.resource.expose.OrderResource;
import com.example.fractalreto.ordersLifecycle.resource.expose.ProductsByOrder;
import com.example.fractalreto.ordersLifecycle.resource.save.SaveOrderResource;
import com.example.fractalreto.ordersLifecycle.resource.save.UpdateProductOrder;
import com.example.fractalreto.ordersLifecycle.service.OrderService;
import com.example.fractalreto.ordersLifecycle.service.ProductOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ProductOrderService productOrderService;

    private Order convertToEntity(SaveOrderResource resourse){
        return mapper.map(resourse, Order.class);
    }

    private OrderResource convertToResource(Order entity){
        return mapper.map(entity, OrderResource.class);
    }

    @GetMapping("/orders")
    public List<OrderResource> getAllOrders(){
        List<OrderResource> orders = orderService.getAllOrders()
                .stream().map(this::convertToResource).toList();
        return orders;
        //return new PageImpl<>(orders, pageable, orders.size());
    }

    @PostMapping("/orders")
    public OrderResource createNewUser(@Valid @RequestBody SaveOrderResource resourse){
        return convertToResource(orderService.creatOrder(convertToEntity(resourse)));
    }

    @PutMapping("/order/{orderId}/product/{productId}")
    public OrderResource assignProducts(@PathVariable(name = "orderId")int orderId,
                                        @PathVariable(name = "productId")int productId,
                                        @RequestBody UpdateProductOrder dto){
        System.out.println("sadsadasdadasdasd");
        return convertToResource(productOrderService.addProductsToOrder(orderId,productId,dto.getQuantity()));
    }

    @PutMapping("/order/{orderId}/remove/product/{productId}")
    public void unassignProducts(@PathVariable(name = "orderId")int orderId,
                                        @PathVariable(name = "productId")int productId){
        productOrderService.unassignProduct(orderId, productId);
    }

    @PutMapping("/order/{orderId}/update/product/{productId}")
    public OrderProducts updateProductQuantityForSpecificOrder(@PathVariable(name = "orderId")int orderId,
                                                               @PathVariable(name = "productId")int productId, @RequestBody UpdateProductOrder dto){
        return productOrderService.updateProductQuantityForExistingOrder(orderId,productId,dto.getQuantity());
    }

    @GetMapping("/order/{orderId}/products")
    public List<ProductsByOrder> getProductsByOrder(@PathVariable (name = "orderId") int orderId){
        return productOrderService.getAllProductsByOrder(orderId);
    }


}
