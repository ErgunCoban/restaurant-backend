package com.example.restaurant.service.impl;

import com.example.restaurant.dto.request.CreateRestaurantTableRequestDTO;
import com.example.restaurant.dto.response.RestaurantTableResponseDTO;
import com.example.restaurant.mapper.RestaurantTableMapper;
import com.example.restaurant.model.RestaurantTable;
import com.example.restaurant.repository.RestaurantTableRepository;
import com.example.restaurant.rules.RestaurantTableRules;
import com.example.restaurant.service.IRestaurantTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantTableServiceImpl implements IRestaurantTableService {

    private final RestaurantTableRepository restaurantTableRepository;
    private final RestaurantTableMapper restaurantTableMapper;
    private final RestaurantTableRules restaurantTableRules;


    @Override
    public RestaurantTableResponseDTO saveRestaurantTable(CreateRestaurantTableRequestDTO createRestaurantTableRequestDTO) {

        restaurantTableRules.checkIfTableAlreadyExists(createRestaurantTableRequestDTO.getName());

        RestaurantTable restaurantTable = restaurantTableMapper.toEntity(createRestaurantTableRequestDTO);
        RestaurantTable savedRestaurantTable = restaurantTableRepository.save(restaurantTable);
        return restaurantTableMapper.toResponse(savedRestaurantTable);
    }
}
