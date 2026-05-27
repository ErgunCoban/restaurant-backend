package com.example.restaurant.dto.response.analysis;

import com.example.restaurant.enums.ReportPeriod;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TableActivityResponseDTO {

    private String tableName;

    private Long orderCount;

}
