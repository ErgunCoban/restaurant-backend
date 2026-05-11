package com.example.restaurant.service;

import com.example.restaurant.dto.request.CreateRestaurantTableRequestDTO;
import com.example.restaurant.dto.response.RestaurantTableResponseDTO;

public interface IRestaurantTableService {

    public RestaurantTableResponseDTO saveRestaurantTable(CreateRestaurantTableRequestDTO createRestaurantTableRequestDTO);

}
