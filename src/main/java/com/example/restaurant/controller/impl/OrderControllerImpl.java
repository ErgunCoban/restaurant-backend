package com.example.restaurant.controller.impl;

import com.example.restaurant.controller.IOrderController;
import com.example.restaurant.controller.base.RestBaseController;
import com.example.restaurant.dto.request.CreateOrderRequestDTO;
import com.example.restaurant.dto.request.UpdateOrderRequest;
import com.example.restaurant.dto.response.OrderResponseDTO;
import com.example.restaurant.enums.OrderStatus;
import com.example.restaurant.model.base.RootEntity;
import com.example.restaurant.service.IOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurant/order")
public class OrderControllerImpl extends RestBaseController implements IOrderController {

    private final IOrderService orderService;


    @PostMapping("/save")
    @Override
    public RootEntity<Void> saveOrder(@Valid @RequestBody CreateOrderRequestDTO createOrderRequestDTO) {
        orderService.saveOrder(createOrderRequestDTO);

        return ok(null);
    }

    @GetMapping("/all-orders")
    @Override
    public RootEntity<List<OrderResponseDTO>> getAllOrders() {
        return ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    @Override
    public RootEntity<OrderResponseDTO> getOrderById(@PathVariable(name = "id") Long id) {
        return ok(orderService.getOrderById(id));
    }

    @PutMapping("/update/{id}")
    @Override
    public RootEntity<Void> updateOrderById(@PathVariable(name = "id") Long id, @RequestBody UpdateOrderRequest updateOrderRequest) {
         orderService.updateOrderById(id, updateOrderRequest);

         return ok(null);
    }

    //orders?status=PREPARING tarzında url ye sahip olacak
    @GetMapping("/orders")
    @Override
    public RootEntity<List<OrderResponseDTO>> getOrdersByStatus(@RequestParam OrderStatus status) {
        return ok(orderService.getOrdersByStatus(status));
    }

}
