package com.example.restaurant.controller.impl;

import com.example.restaurant.controller.IRestRestaurantTableController;
import com.example.restaurant.dto.request.CreateRestaurantTableRequestDTO;
import com.example.restaurant.service.IRestaurantTableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/api/restaurant/table")
public class RestRestaurantTableControllerImpl implements IRestRestaurantTableController {

    private final IRestaurantTableService restaurantTableService;

    @PostMapping("/save")
    @Override
    public void saveRestaurantTable(@Valid @RequestBody CreateRestaurantTableRequestDTO createRestaurantTableRequestDTO) {
        restaurantTableService.saveRestaruantTable(createRestaurantTableRequestDTO);
    }

}
