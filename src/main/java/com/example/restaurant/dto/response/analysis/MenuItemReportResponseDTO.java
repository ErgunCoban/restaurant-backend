package com.example.restaurant.dto.response.analysis;

import com.example.restaurant.enums.ReportPeriod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemReportResponseDTO {

    private List<MenuItemCountDTO> menuItemCounts;

    private ReportPeriod period;

    private LocalDate periodStart;

    private LocalDate periodEnd;

}
