package com.example.restaurant.dto.response.analysis;

import com.example.restaurant.enums.ReportPeriod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class OrderCountResponseDTO {

    private Long orderCount;

    private Long previousPeriodCount;

    private Double changePercentage;

    private ReportPeriod period;

    private LocalDate periodStart;

    private LocalDate periodEnd;

}
