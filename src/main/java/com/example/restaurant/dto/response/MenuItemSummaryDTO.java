package com.example.restaurant.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MenuItemSummaryDTO {

    private String name;

    private BigDecimal price;

}
