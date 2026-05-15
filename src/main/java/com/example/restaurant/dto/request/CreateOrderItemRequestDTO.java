package com.example.restaurant.dto.request;

import com.example.restaurant.model.MenuItem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateOrderItemRequestDTO {

    @NotNull
    private int quantity;

    @NotNull
    private BigDecimal unitPrice;

    @NotNull
    private Long menuItemId;

}
