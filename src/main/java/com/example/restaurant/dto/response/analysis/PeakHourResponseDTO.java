package com.example.restaurant.dto.response.analysis;

import com.example.restaurant.enums.ReportPeriod;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class PeakHourResponseDTO {

    private Integer peakHour;

    private Long orderCountAtPeakHour;

    private Double changePercentage;

    private ReportPeriod period;

    private LocalDate periodStart;

    private LocalDate periodEnd;

}
