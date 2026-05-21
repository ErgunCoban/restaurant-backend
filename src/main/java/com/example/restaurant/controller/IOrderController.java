package com.example.restaurant.controller;

import com.example.restaurant.dto.request.CreateOrderRequestDTO;
import com.example.restaurant.dto.request.UpdateOrderRequest;
import com.example.restaurant.dto.response.OrderResponseDTO;
import com.example.restaurant.enums.OrderStatus;
import com.example.restaurant.model.base.RootEntity;

import java.util.List;

public interface IOrderController {

    RootEntity<Void> saveOrder(CreateOrderRequestDTO createOrderRequestDTO);

    RootEntity<List<OrderResponseDTO>> getAllOrders();

    RootEntity<OrderResponseDTO> getOrderById(Long id);

    RootEntity<Void> updateOrderById(Long id, UpdateOrderRequest updateOrderRequest);

    RootEntity<List<OrderResponseDTO>> getOrdersByStatus(OrderStatus status);

}
