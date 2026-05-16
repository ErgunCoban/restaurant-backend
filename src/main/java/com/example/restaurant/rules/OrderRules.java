package com.example.restaurant.rules;

import com.example.restaurant.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderRules {

    private final OrderRepository orderRepository;



}
