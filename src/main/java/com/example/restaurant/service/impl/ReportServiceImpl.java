package com.example.restaurant.service.impl;

import com.example.restaurant.dto.response.analysis.RevenueReportResponseDTO;
import com.example.restaurant.enums.ReportPeriod;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.service.IReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements IReportService {

    private final OrderRepository orderRepository;

    private LocalDate[] getDateRange(ReportPeriod period) {
        LocalDate today = LocalDate.now();

        return switch (period) {
            case DAILY -> new LocalDate[]{today, today.plusDays(1)};
            case WEEKLY -> {
                LocalDate start = today.with(DayOfWeek.MONDAY);
                yield new LocalDate[]{start, start.plusWeeks(1)};
            }
            case MONTHLY -> {
                LocalDate start = today.withDayOfMonth(1);
                yield new LocalDate[]{start, start.plusMonths(1)};
            }
            case YEARLY -> {
                LocalDate start = today.withDayOfYear(1);
                yield new LocalDate[]{start, start.plusYears(1)};
            }
            default -> throw new IllegalArgumentException("Unknown period: " + period);
        };
    }

    @Override
    public RevenueReportResponseDTO getRevenueReport(ReportPeriod period) {
        LocalDate[] currentRange = getDateRange(period);
        LocalDate currentStart = currentRange[0];
        LocalDate currentEnd = currentRange[1];

        LocalDate previousStart = null;
        LocalDate previousEnd = currentStart;

        switch (period) {
            case DAILY -> previousStart = currentStart.minusDays(1);
            case WEEKLY -> previousStart = currentStart.minusWeeks(1);
            case MONTHLY -> previousStart = currentStart.minusMonths(1);
            case YEARLY -> previousStart = currentStart.minusYears(1);
        }

        Date dbCurrentStart = java.sql.Date.valueOf(currentStart);
        Date dbCurrentEnd = java.sql.Date.valueOf(currentEnd);
        Date dbPreviousStart = java.sql.Date.valueOf(previousStart);
        Date dbPreviousEnd = java.sql.Date.valueOf(previousEnd);

        BigDecimal totalRevenue = orderRepository.getTotalRevenueBetween(dbCurrentStart, dbCurrentEnd);
        if (totalRevenue == null){
            totalRevenue = BigDecimal.ZERO;
        }

        BigDecimal previousTotalRevenue = orderRepository.getTotalRevenueBetween(dbPreviousStart, dbPreviousEnd);
        if (previousTotalRevenue == null){
            previousTotalRevenue = BigDecimal.ZERO;
        }

        Double changePercentage = calculateChangePercentage(totalRevenue, previousTotalRevenue);

        return new RevenueReportResponseDTO(
                totalRevenue,
                previousTotalRevenue,
                changePercentage,
                period,
                currentStart,
                currentEnd
        );
    }

    private Double calculateChangePercentage(BigDecimal current, BigDecimal previous){
        if (previous.compareTo(BigDecimal.ZERO) == 0){
            return current.compareTo(BigDecimal.ZERO) > 0 ? 100.0 : 0.0;
        }

        BigDecimal difference = current.subtract(previous);
        BigDecimal percentage = difference
                .multiply(BigDecimal.valueOf(100))
                .divide(previous, 2, RoundingMode.HALF_UP);

        return percentage.doubleValue();
    }

}
