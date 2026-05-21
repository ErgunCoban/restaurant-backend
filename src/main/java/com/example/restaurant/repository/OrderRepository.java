package com.example.restaurant.repository;

import com.example.restaurant.dto.response.OrderResponseDTO;
import com.example.restaurant.enums.OrderStatus;
import com.example.restaurant.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Long> {

    List<Order> findByStatus(OrderStatus status);

}
