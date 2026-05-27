package com.example.restaurant.service.impl;

import com.example.restaurant.dto.response.analysis.*;
import com.example.restaurant.enums.ReportPeriod;
import com.example.restaurant.repository.OrderItemRepository;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.service.IReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements IReportService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

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

    @Override
    public OrderCountResponseDTO getTotalOrderCount(ReportPeriod period) {
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


        Long totalOrderCount = orderRepository.getTotalOrderCountBetween(dbCurrentStart, dbCurrentEnd);
        if (totalOrderCount == null) {
            totalOrderCount = 0L;
        }

        Long previousTotalOrderCount = orderRepository.getTotalOrderCountBetween(dbPreviousStart, dbPreviousEnd);
        if (previousTotalOrderCount == null) {
            previousTotalOrderCount = 0L;
        }

        Double changePercentage = calculateLongChangePercentage(totalOrderCount, previousTotalOrderCount);


        return new OrderCountResponseDTO(
                totalOrderCount,
                previousTotalOrderCount,
                changePercentage,
                period,
                currentStart,
                currentEnd
        );
    }

    @Override
    public PeakHourResponseDTO getPeakHourReport(ReportPeriod period) {

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


        List<Object[]> currentResult = orderRepository.findPeakHourWithCount(dbCurrentStart, dbCurrentEnd);
        Integer currentPeakHour = 0;
        Long currentPeakHourOrderCount = 0L;

        if (currentResult != null && !currentResult.isEmpty()) {
            Object[] row = currentResult.get(0);
            if (row[0] != null) currentPeakHour = ((Number) row[0]).intValue();
            if (row[1] != null) currentPeakHourOrderCount = ((Number) row[1]).longValue();
        }


        List<Object[]> previousResult = orderRepository.findPeakHourWithCount(dbPreviousStart, dbPreviousEnd);
        Long previousPeakHourOrderCount = 0L;

        if (previousResult != null && !previousResult.isEmpty()) {
            Object[] row = previousResult.get(0);
            if (row[1] != null) previousPeakHourOrderCount = ((Number) row[1]).longValue();
        }


        Double changePercentage = calculateLongChangePercentage(currentPeakHourOrderCount, previousPeakHourOrderCount);


        return new PeakHourResponseDTO(
                currentPeakHour,
                currentPeakHourOrderCount,
                changePercentage,
                period,
                currentStart,
                currentEnd
        );
    }

    @Override
    public TableActivityListResponseDTO getMostActiveTablesReport(ReportPeriod period) {
        LocalDate[] periodRange = getDateRange(period);
        LocalDate periodStart = periodRange[0];
        LocalDate periodEnd = periodRange[1];

        Date dbPeriodStart = java.sql.Date.valueOf(periodStart);
        Date dbPeriodEnd = java.sql.Date.valueOf(periodEnd);

        List<Object[]> results = orderRepository.findMostActiveTables(dbPeriodStart, dbPeriodEnd);

        List<TableActivityResponseDTO> tablesActivity = new ArrayList<>();

        if (!results.isEmpty()){

            for (Object[] row : results){
                TableActivityResponseDTO dto = new TableActivityResponseDTO();
                dto.setTableName(row[0].toString());
                dto.setOrderCount(((Number) row[1]).longValue());
                tablesActivity.add(dto);
            }

            return new TableActivityListResponseDTO(
                    tablesActivity,
                    tablesActivity.isEmpty() ? null : tablesActivity.get(0),
                    period,
                    periodStart,
                    periodEnd
            );

        }else{
            return null;
        }

    }

    @Override
    public MenuItemReportResponseDTO getMenuItemReport(ReportPeriod period) {
        LocalDate[] periodRange = getDateRange(period);
        LocalDate periodStart = periodRange[0];
        LocalDate periodEnd = periodRange[1];

        Date dbPeriodStart = java.sql.Date.valueOf(periodStart);
        Date dbPeriodEnd = java.sql.Date.valueOf(periodEnd);

        List<Object[]> results = orderItemRepository.findMostOrderedProducts(dbPeriodStart, dbPeriodEnd);

        List<MenuItemCountDTO> menuItemOrderCounts = new ArrayList<>();

        if (!results.isEmpty()){

            for (Object[] row : results){
                MenuItemCountDTO dto = new MenuItemCountDTO();
                dto.setName(row[0].toString());
                dto.setCount(((Number) row[1]).longValue());
                menuItemOrderCounts.add(dto);
            }

            return new MenuItemReportResponseDTO(
                    menuItemOrderCounts,
                    period,
                    periodStart,
                    periodEnd
            );
        }else {
            return null;
        }

    }


    private Double calculateLongChangePercentage(Long current, Long previous) {
        if (previous == 0) {
            return current > 0 ? 100.0 : 0.0;
        }
        double difference = current - previous;
        double percentage = (difference / previous) * 100;
        return Math.round(percentage * 100.0) / 100.0;
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
