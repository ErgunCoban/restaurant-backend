package com.example.restaurant.controller;

import com.example.restaurant.dto.request.CreateOrderRequestDTO;
import com.example.restaurant.dto.response.OrderResponseDTO;
import com.example.restaurant.model.base.RootEntity;

import java.util.List;

public interface IOrderController {

    RootEntity<Void> saveOrder(CreateOrderRequestDTO createOrderRequestDTO);

    RootEntity<Void> cancelOrder(Long id);

    RootEntity<List<OrderResponseDTO>> getAllOrders();

    RootEntity<OrderResponseDTO> getOrderById(Long id);
}
