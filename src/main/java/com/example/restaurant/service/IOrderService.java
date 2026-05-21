package com.example.restaurant.service;

import com.example.restaurant.dto.request.CreateOrderRequestDTO;
import com.example.restaurant.dto.request.UpdateOrderRequest;
import com.example.restaurant.dto.response.OrderResponseDTO;
import com.example.restaurant.enums.OrderStatus;

import java.util.List;

public interface IOrderService {

     void saveOrder(CreateOrderRequestDTO createOrderRequestDTO);

     List<OrderResponseDTO> getAllOrders();

     OrderResponseDTO getOrderById(Long id);

     void updateOrderById(Long id, UpdateOrderRequest updateOrderRequest);

     List<OrderResponseDTO> getOrdersByStatus(OrderStatus status);


}
