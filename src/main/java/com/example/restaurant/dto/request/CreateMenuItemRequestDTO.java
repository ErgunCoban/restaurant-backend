package com.example.restaurant.dto.request;

import com.example.restaurant.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateMenuItemRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private BigDecimal price;

    @NotBlank
    private String imageUrl;

    @NotNull
    private Boolean isAvailable;

    @NotNull
    private int prepTimeMin;

    @NotNull
    private Long categoryId;

}

