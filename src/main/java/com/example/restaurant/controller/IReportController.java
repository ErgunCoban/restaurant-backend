package com.example.restaurant.controller;

import com.example.restaurant.dto.response.analysis.RevenueReportResponseDTO;
import com.example.restaurant.enums.ReportPeriod;
import com.example.restaurant.model.base.RootEntity;

public interface IReportController {

    RootEntity<RevenueReportResponseDTO> getRevenueReport(ReportPeriod period);

}
