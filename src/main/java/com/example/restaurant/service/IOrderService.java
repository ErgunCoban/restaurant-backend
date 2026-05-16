package com.example.restaurant.service;

import com.example.restaurant.dto.request.CreateOrderRequestDTO;
import com.example.restaurant.dto.response.OrderResponseDTO;

import java.util.List;

public interface IOrderService {

     void saveOrder(CreateOrderRequestDTO createOrderRequestDTO);

     void cancelOrder(long id);

     List<OrderResponseDTO> getAllOrders();

     OrderResponseDTO getOrderById(Long id);


}
