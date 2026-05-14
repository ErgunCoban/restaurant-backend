package com.example.restaurant.dto.request;

import com.example.restaurant.model.Category;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMenuItemRequestDTO {

    private String name;

    private String description;

    private Double price;

    private String imageUrl;

    private Boolean isAvailable;

    private int prepTimeMin;

}
