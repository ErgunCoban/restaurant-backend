package com.example.restaurant.service;

import com.example.restaurant.dto.response.analysis.*;
import com.example.restaurant.enums.ReportPeriod;

import java.util.List;

public interface IReportService {

    RevenueReportResponseDTO getRevenueReport(ReportPeriod period);

    OrderCountResponseDTO getTotalOrderCount(ReportPeriod period);

    PeakHourResponseDTO getPeakHourReport(ReportPeriod period);

    TableActivityListResponseDTO getMostActiveTablesReport(ReportPeriod period);

    MenuItemReportResponseDTO getMenuItemReport(ReportPeriod period);

}
