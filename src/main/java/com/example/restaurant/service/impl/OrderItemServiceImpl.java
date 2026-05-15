package com.example.restaurant.service.impl;

import com.example.restaurant.dto.request.CreateOrderItemRequestDTO;
import com.example.restaurant.mapper.OrderItemMapper;
import com.example.restaurant.mapper.OrderMapper;
import com.example.restaurant.model.Order;
import com.example.restaurant.model.OrderItem;
import com.example.restaurant.repository.OrderItemRepository;
import com.example.restaurant.service.IOrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements IOrderItemService {

    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;


    @Override
    public void save(CreateOrderItemRequestDTO createOrderItemRequestDTO) {

        OrderItem orderItem = orderItemMapper.toEntity(createOrderItemRequestDTO);

        orderItemRepository.save(orderItem);


    }
}
