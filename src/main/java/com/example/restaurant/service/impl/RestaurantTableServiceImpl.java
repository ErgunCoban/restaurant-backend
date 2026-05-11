package com.example.restaurant.service.impl;

import com.example.restaurant.dto.request.CreateRestaurantTableRequestDTO;
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
    public void saveRestaruantTable(CreateRestaurantTableRequestDTO createRestaurantTableRequestDTO) {
        RestaurantTable restaurantTable = restaurantTableMapper.toEntity(createRestaurantTableRequestDTO);
        restaurantTableRepository.save(restaurantTable);
    }
}
