package com.example.restaurant.service.impl;

import com.example.restaurant.dto.request.CreateOrderRequestDTO;
import com.example.restaurant.dto.response.OrderResponseDTO;
import com.example.restaurant.enums.OrderStatus;
import com.example.restaurant.exception.BaseException;
import com.example.restaurant.exception.ErrorMessage;
import com.example.restaurant.exception.MessageType;
import com.example.restaurant.mapper.MenuItemMapper;
import com.example.restaurant.mapper.OrderItemMapper;
import com.example.restaurant.mapper.OrderMapper;
import com.example.restaurant.model.MenuItem;
import com.example.restaurant.model.Order;
import com.example.restaurant.model.OrderItem;
import com.example.restaurant.model.RestaurantTable;
import com.example.restaurant.repository.MenuItemRepository;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.rules.RestaurantTableRules;
import com.example.restaurant.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final RestaurantTableRules restaurantTableRules;


    @Override
    public void saveOrder(CreateOrderRequestDTO createOrderRequestDTO) {

        RestaurantTable restaurantTable = restaurantTableRules.checkIfTableExistsById(createOrderRequestDTO.getRestaurantTableId());

        Order order = orderMapper.toEntity(createOrderRequestDTO);
        order.setTable(restaurantTable);

        List<OrderItem> orderItems = createOrderRequestDTO.getOrderItems().stream().map(itemDTO -> {
            MenuItem menuItem = menuItemRepository.findById(itemDTO.getMenuItemId())
                    .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXISTS, itemDTO.getMenuItemId().toString())));

            OrderItem orderItem = orderItemMapper.toEntity(itemDTO);
            orderItem.setMenuItem(menuItem);
            orderItem.setUnitPrice(menuItem.getPrice());
            orderItem.setOrder(order);

            return orderItem;
        }).toList();

        order.setOrderItems(orderItems);

        BigDecimal totalPrice = orderItems.stream()
                .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalPrice(totalPrice);

        orderRepository.save(order);

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
