package com.example.restaurant.repository;

import com.example.restaurant.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query(value = "SELECT m.name, COUNT(*) as item_count " +
            "FROM restaurant.order_items oi " +
            "JOIN restaurant.menu_items m ON oi.menu_item_id = m.id " +
            "JOIN restaurant.orders o ON oi.order_id = o.id " +
            "WHERE o.create_time BETWEEN :startDate AND :endDate " +
            "GROUP BY m.name " +
            "ORDER BY item_count DESC",
            nativeQuery = true)
    List<Object[]> findMostOrderedProducts(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
