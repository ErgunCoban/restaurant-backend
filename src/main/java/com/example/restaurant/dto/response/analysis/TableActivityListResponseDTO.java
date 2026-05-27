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
public class TableActivityListResponseDTO {

    private List<TableActivityResponseDTO> tablesActivity;  //tüm masaların durumu

    private TableActivityResponseDTO mostActiveTable;   //en aktif masa

    private ReportPeriod period;

    private LocalDate periodStart;

    private LocalDate periodEnd;

}
