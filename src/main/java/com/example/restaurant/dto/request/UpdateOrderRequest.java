package com.example.restaurant.dto.request;

import com.example.restaurant.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderRequest {

    @NotNull
    OrderStatus status;

}
