package com.bookstoreapplication.repository;

import com.bookstoreapplication.module.CartModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartModule, Integer> {
}