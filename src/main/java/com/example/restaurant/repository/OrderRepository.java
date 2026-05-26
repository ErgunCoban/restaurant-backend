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

}
