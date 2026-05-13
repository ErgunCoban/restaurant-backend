package com.example.restaurant.controller.impl;

import com.example.restaurant.controller.IRestaurantTableController;
import com.example.restaurant.controller.base.RestBaseController;
import com.example.restaurant.dto.request.CreateRestaurantTableRequestDTO;
import com.example.restaurant.dto.response.RestaurantTableResponseDTO;
import com.example.restaurant.model.base.RootEntity;
import com.example.restaurant.service.IRestaurantTableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/api/restaurant/table")
public class RestaurantTableControllerImpl extends RestBaseController implements IRestaurantTableController {

    private final IRestaurantTableService restaurantTableService;

    @PostMapping("/save")
    @Override
    public RootEntity<RestaurantTableResponseDTO> saveRestaurantTable(@Valid @RequestBody CreateRestaurantTableRequestDTO createRestaurantTableRequestDTO) {
        return ok(restaurantTableService.saveRestaurantTable(createRestaurantTableRequestDTO));
    }

    @GetMapping("/list")
    @Override
    public RootEntity<List<RestaurantTableResponseDTO>> getAllRestaurantTables() {
        return ok(restaurantTableService.getAllRestaurantTables());
    }

    @PutMapping("/update/{id}")
    @Override
    public RootEntity<RestaurantTableResponseDTO> updateRestaurantTable(
            @PathVariable Long id,
            @Valid @RequestBody CreateRestaurantTableRequestDTO createRestaurantTableRequestDTO) {
        return ok(restaurantTableService.updateRestaurantTable(id, createRestaurantTableRequestDTO)
        );
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public RootEntity<Void> deleteRestaurantTable(@PathVariable Long id) {
        restaurantTableService.deleteRestaurantTable(id);
        return ok(null);
    }

}
