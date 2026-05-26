package com.example.restaurant.service;

import com.example.restaurant.dto.response.analysis.RevenueReportResponseDTO;
import com.example.restaurant.enums.ReportPeriod;

public interface IReportService {

    RevenueReportResponseDTO getRevenueReport(ReportPeriod period);

}
