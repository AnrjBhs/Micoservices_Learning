package com.anuraj.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anuraj.orderservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
