package com.example.restaurant.service.impl;

import com.example.restaurant.dto.request.CreateRestaurantTableRequestDTO;
import com.example.restaurant.dto.response.RestaurantTableResponseDTO;
import com.example.restaurant.mapper.RestaurantTableMapper;
import com.example.restaurant.model.RestaurantTable;
import com.example.restaurant.repository.RestaurantTableRepository;
import com.example.restaurant.service.IRestaurantTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantTableServiceImpl implements IRestaurantTableService {

    private final RestaurantTableRepository restaurantTableRepository;

    private final RestaurantTableMapper restaurantTableMapper;

    @Override
    public RestaurantTableResponseDTO saveRestaurantTable(CreateRestaurantTableRequestDTO createRestaurantTableRequestDTO) {
        RestaurantTable restaurantTable = restaurantTableMapper.toEntity(createRestaurantTableRequestDTO);
        RestaurantTable savedRestaurantTable = restaurantTableRepository.save(restaurantTable);
        return restaurantTableMapper.toResponse(savedRestaurantTable);
    }
}
