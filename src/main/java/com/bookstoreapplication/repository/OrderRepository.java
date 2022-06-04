package com.bookstoreapplication.repository;

import com.bookstoreapplication.module.OrderModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderModule,Integer> {
}