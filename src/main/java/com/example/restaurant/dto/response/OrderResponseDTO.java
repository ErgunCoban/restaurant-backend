package com.example.restaurant.dto.response;

import com.example.restaurant.enums.OrderStatus;
import com.example.restaurant.model.OrderItem;
import com.example.restaurant.model.RestaurantTable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderResponseDTO {

    private Long id;

    private Date createTime;

    private BigDecimal totalPrice;

    private OrderStatus status;

    private RestaurantTableResponseDTO table;

    private List<OrderItemResponseDTO> orderItems;

}
