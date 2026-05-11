package com.example.restaurant.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRestaurantTableRequestDTO {

    @NotBlank
    private String name;

}
