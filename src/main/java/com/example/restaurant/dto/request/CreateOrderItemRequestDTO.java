package com.example.restaurant.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CreateOrderItemRequestDTO {

    @NotNull
    private int quantity;

    @NotNull
    private BigDecimal unitPrice;
}
