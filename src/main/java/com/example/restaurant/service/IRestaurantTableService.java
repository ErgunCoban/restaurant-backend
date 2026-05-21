package com.example.restaurant.service;

import com.example.restaurant.dto.request.CreateRestaurantTableRequestDTO;
import com.example.restaurant.dto.response.RestaurantTableResponseDTO;

import java.util.List;

public interface IRestaurantTableService {

    RestaurantTableResponseDTO saveRestaurantTable(CreateRestaurantTableRequestDTO createRestaurantTableRequestDTO);

    List<RestaurantTableResponseDTO> getAllRestaurantTables();

    RestaurantTableResponseDTO updateRestaurantTable(Long id, CreateRestaurantTableRequestDTO createRestaurantTableRequestDTO);

    void deleteRestaurantTable(Long id);

    RestaurantTableResponseDTO getRestaurantTableById(Long id);

}
