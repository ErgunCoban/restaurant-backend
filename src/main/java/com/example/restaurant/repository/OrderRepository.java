package com.example.restaurant.repository;

import com.example.restaurant.enums.OrderStatus;
import com.example.restaurant.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Long> {

    List<Order> findByStatus(OrderStatus status);

    @Query("SELECT SUM(o.totalPrice) FROM Order o WHERE o.createTime >= CAST(:startDate AS date) AND o.createTime < CAST(:endDate AS date)")
    BigDecimal getTotalRevenueBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


    @Query("SELECT COUNT(o) FROM Order o WHERE o.createTime >= :startDate AND o.createTime < :endDate")
    Long getTotalOrderCountBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


    @Query(value = "SELECT EXTRACT(HOUR FROM o.create_time) AS order_hour, COUNT(*) AS count_order " +
            "FROM restaurant.orders o " +
            "WHERE o.create_time BETWEEN :startDate AND :endDate " +
            "GROUP BY order_hour " +
            "ORDER BY count_order DESC " +
            "LIMIT 1", nativeQuery = true)
    List<Object[]> findPeakHourWithCount(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


    @Query(value = "SELECT rt.name, COUNT(*) as order_count " +
            "FROM restaurant.orders o JOIN restaurant.restaurant_table rt ON o.restaurant_table_id = rt.id " +
            "WHERE o.create_time BETWEEN :startDate AND :endDate " +
            "GROUP BY rt.name " +
            "ORDER BY order_count DESC",
            nativeQuery = true)
    List<Object[]> findMostActiveTables(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
