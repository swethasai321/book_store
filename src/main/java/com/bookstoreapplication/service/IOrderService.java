package com.bookstoreapplication.service;

import com.bookstoreapplication.controller.UserRegistration;
import com.bookstoreapplication.dto.OrderDTO;
import com.bookstoreapplication.module.BookModule;
import com.bookstoreapplication.module.OrderModule;
import com.bookstoreapplication.module.OrderModule;
import com.bookstoreapplication.module.UserRegistrationModule;

import java.util.List;

public interface IOrderService {

    OrderModule placeOrder(OrderDTO orderDTO);

    OrderModule getOrderID(Integer id);

    List<OrderModule> getAllOrders();

    OrderModule cancelOrder(int id);
}