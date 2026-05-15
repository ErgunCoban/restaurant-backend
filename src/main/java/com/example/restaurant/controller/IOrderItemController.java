package com.example.restaurant.controller;

import com.example.restaurant.dto.request.CreateOrderItemRequestDTO;
import com.example.restaurant.model.base.RootEntity;

import java.util.List;


public interface IOrderItemController {

    RootEntity<Void> save(CreateOrderItemRequestDTO orderItemRequestDTOList);


}
