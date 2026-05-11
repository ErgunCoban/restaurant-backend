package com.example.restaurant.controller;

import com.example.restaurant.dto.request.CreateRestaurantTableRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

public interface IRestRestaurantTableController {

    public void saveRestaurantTable(CreateRestaurantTableRequestDTO createRestaurantTableRequestDTO);

}
