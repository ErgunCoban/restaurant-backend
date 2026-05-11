package com.example.restaurant.dto.response;

import com.example.restaurant.model.MenuItem;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemResponseDTO {

    private int quantity;

    private BigDecimal unitPrice;

    private BigDecimal totalPrice;

    private MenuItemSummaryDTO menuItem;

}
