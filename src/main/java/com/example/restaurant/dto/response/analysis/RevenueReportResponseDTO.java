package com.example.restaurant.dto.response.analysis;

import com.example.restaurant.enums.ReportPeriod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RevenueReportResponseDTO {

    private BigDecimal totalRevenue;

    private BigDecimal previousPeriodRevenue;

    private Double changePercentage;

    private ReportPeriod period;

    private LocalDate periodStart;

    private LocalDate periodEnd;


}
