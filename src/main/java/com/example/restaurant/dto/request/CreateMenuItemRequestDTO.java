package com.example.restaurant.dto.request;

import com.example.restaurant.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMenuItemRequestDTO {

    private String name;

    private String description;

    private Double price;

    private String imageUrl;

    private Boolean isAvailable;

    private int prepTimeMin;

    private Category category;

}

