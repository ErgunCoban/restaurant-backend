package com.example.restaurant.controller.impl;

import com.example.restaurant.controller.IOrderController;
import com.example.restaurant.controller.base.RestBaseController;
import com.example.restaurant.dto.request.CreateOrderRequestDTO;
import com.example.restaurant.dto.response.OrderResponseDTO;
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

    @DeleteMapping("/delete/{id}")
    @Override
    public RootEntity<Void> cancelOrder( @PathVariable(name = "id") Long id) {
        orderService.cancelOrder(id);

        return ok(null);
    }

    @GetMapping("/list")
    @Override
    public RootEntity<List<OrderResponseDTO>> getAllOrders() {

        return ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    @Override
    public RootEntity<OrderResponseDTO> getOrderById(@PathVariable(name = "id") Long id) {

        return ok(orderService.getOrderById(id));
    }
}
