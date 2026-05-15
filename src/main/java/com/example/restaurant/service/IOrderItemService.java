package com.example.restaurant.service;

import com.example.restaurant.dto.request.CreateOrderItemRequestDTO;

import java.util.List;

public interface IOrderItemService {

    void save(List<CreateOrderItemRequestDTO> createOrderItemRequestDTOList);

}
