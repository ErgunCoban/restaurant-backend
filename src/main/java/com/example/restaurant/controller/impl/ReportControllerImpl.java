package com.example.restaurant.controller.impl;

import com.example.restaurant.controller.IReportController;
import com.example.restaurant.controller.base.RestBaseController;
import com.example.restaurant.dto.response.analysis.RevenueReportResponseDTO;
import com.example.restaurant.enums.ReportPeriod;
import com.example.restaurant.model.base.RootEntity;
import com.example.restaurant.service.IReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurant/report")
public class ReportControllerImpl extends RestBaseController implements IReportController {

    private final IReportService reportService;

    @GetMapping("/revenue")
    @Override
    public RootEntity<RevenueReportResponseDTO> getRevenueReport(@RequestParam ReportPeriod period) {
        return ok(reportService.getRevenueReport(period));
    }

}
