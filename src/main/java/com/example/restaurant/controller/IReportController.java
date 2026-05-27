package com.example.restaurant.controller;

import com.example.restaurant.dto.response.analysis.*;
import com.example.restaurant.enums.ReportPeriod;
import com.example.restaurant.model.base.RootEntity;

public interface IReportController {

    RootEntity<RevenueReportResponseDTO> getRevenueReport(ReportPeriod period);

    RootEntity<OrderCountResponseDTO> getTotalOrderCount(ReportPeriod period);

    RootEntity<PeakHourResponseDTO> getPeakHourReport(ReportPeriod period);

    RootEntity<TableActivityListResponseDTO> getMostActiveTablesReport(ReportPeriod period);

    RootEntity<MenuItemReportResponseDTO> getMenuItemReport(ReportPeriod period);

}
