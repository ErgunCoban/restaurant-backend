package com.example.restaurant.service;

import com.example.restaurant.dto.request.CreateOrderItemRequestDTO;



public interface IOrderItemService {

    void save(CreateOrderItemRequestDTO createOrderItemRequestDTO);

}
