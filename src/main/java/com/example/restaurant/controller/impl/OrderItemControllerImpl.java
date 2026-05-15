package com.example.restaurant.controller.impl;

import com.example.restaurant.controller.IOrderItemController;
import com.example.restaurant.dto.request.CreateOrderItemRequestDTO;
import com.example.restaurant.model.base.RootEntity;
import com.example.restaurant.service.IOrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.restaurant.model.base.RootEntity.ok;

@RestController
@RequestMapping("/api/restaurant/order-item")
@RequiredArgsConstructor
public class OrderItemControllerImpl implements IOrderItemController {

    public final IOrderItemService orderItemService;


    @PostMapping("/save")
    @Override
    public RootEntity<Void> save(CreateOrderItemRequestDTO orderItemRequestDTO) {

        orderItemService.save(orderItemRequestDTO);
        return ok(null);
    }
}

