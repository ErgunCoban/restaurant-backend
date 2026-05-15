package com.example.restaurant.service.impl;

import com.example.restaurant.dto.request.CreateOrderRequestDTO;
import com.example.restaurant.dto.response.OrderResponseDTO;
import com.example.restaurant.mapper.OrderMapper;
import com.example.restaurant.model.Order;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    @Override
    public void saveOrder(List<CreateOrderRequestDTO> createOrderRequestDTOList) {

        List<Order> order = orderMapper.toEntityList(createOrderRequestDTOList);
        orderRepository.saveAll(order);

    }

    @Override
    public void cancelOrder(long id) {

        orderRepository.deleteById(id);

    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {

        List<Order> orders = orderRepository.findAll();
        return orderMapper.toResponseDTOList(orders);
    }


    @Override
    public OrderResponseDTO getOrderById(Long id) {

        Order order = orderRepository.findById(id).get();
        return   orderMapper.toResponse(order);
    }
}
