package com.example.restaurant.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemResponseDTO {

    private String name;

    private String description;

    private Double price;

    private String imageUrl;

    private Boolean isAvailable;

    private int prepTimeMin;

}
