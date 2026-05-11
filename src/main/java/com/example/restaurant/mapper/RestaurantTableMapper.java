package com.example.restaurant.mapper;

import com.example.restaurant.dto.request.CreateRestaurantTableRequestDTO;
import com.example.restaurant.dto.response.RestaurantTableResponseDTO;
import com.example.restaurant.model.RestaurantTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantTableMapper {

    @Mapping(target = "id", ignore = true)
    RestaurantTable toEntity(CreateRestaurantTableRequestDTO createRestaurantTableRequestDTO);

    RestaurantTableResponseDTO toResponse(RestaurantTable restaurantTable);

}
