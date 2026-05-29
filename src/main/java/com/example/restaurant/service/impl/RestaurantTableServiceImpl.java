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
import com.example.restaurant.enums.OrderStatus;
import com.example.restaurant.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantTableServiceImpl implements IRestaurantTableService {

    private final RestaurantTableRepository restaurantTableRepository;
    private final OrderRepository orderRepository;
    private final RestaurantTableRules restaurantTableRules;
    private final RestaurantTableMapper restaurantTableMapper;

    @Override
    public RestaurantTableResponseDTO saveRestaurantTable(CreateRestaurantTableRequestDTO createRestaurantTableRequestDTO) {
        restaurantTableRules.checkIfTableAlreadyExists(createRestaurantTableRequestDTO.getName());
        RestaurantTable restaurantTable = restaurantTableMapper.toEntity(createRestaurantTableRequestDTO);
        RestaurantTable savedRestaurantTable = restaurantTableRepository.save(restaurantTable);
        return restaurantTableMapper.toResponse(savedRestaurantTable);
    }

    @Override
    public List<RestaurantTableResponseDTO> getAllRestaurantTables() {

        List<RestaurantTable> restaurantTables = restaurantTableRepository.findAll();
        return restaurantTableMapper.toResponseList(restaurantTables);
    }

    @Override
    public RestaurantTableResponseDTO updateRestaurantTable(Long id,CreateRestaurantTableRequestDTO createRestaurantTableRequestDTO) {

        restaurantTableRules.checkIfTableExistsById(id);
        RestaurantTable restaurantTable = restaurantTableRepository.findById(id).get();
        restaurantTable.setName(createRestaurantTableRequestDTO.getName());
        RestaurantTable updatedRestaurantTable = restaurantTableRepository.save(restaurantTable);
        return restaurantTableMapper.toResponse(updatedRestaurantTable);
    }

    @Override
    public void deleteRestaurantTable(Long id) {

        restaurantTableRules.checkIfTableExistsById(id);
        RestaurantTable restaurantTable = restaurantTableRepository.findById(id).get();
        restaurantTableRepository.delete(restaurantTable);
    }

    @Override
    public RestaurantTableResponseDTO getRestaurantTableById(Long id) {
        restaurantTableRules.checkIfTableExistsById(id);

        RestaurantTable restaurantTable = restaurantTableRepository.findById(id).get();
        return restaurantTableMapper.toResponse(restaurantTable);
    }

    @Override
    public Boolean hasActiveOrder(Long tableId) {
        restaurantTableRules.checkIfTableExistsById(tableId);
        return orderRepository.existsByTable_IdAndStatus(tableId, OrderStatus.PREPARING);
    }
}
