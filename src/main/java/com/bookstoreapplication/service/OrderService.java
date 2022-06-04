package com.bookstoreapplication.service;


import com.bookstoreapplication.dto.OrderDTO;
import com.bookstoreapplication.email.EmailService;
import com.bookstoreapplication.exception.BookStoreExceptionHandler;
import com.bookstoreapplication.module.BookModule;
import com.bookstoreapplication.module.OrderModule;
import com.bookstoreapplication.module.UserRegistrationModule;
import com.bookstoreapplication.repository.BookStoreRepository;
import com.bookstoreapplication.repository.UserRegistrationRepository;
import com.bookstoreapplication.repository.OrderRepository;
import com.bookstoreapplication.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService{

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    IUserService iUserService;
    @Autowired
    BookStoreRepository bookStoreRepository;
    @Autowired
    IBookService iBookService;
    @Autowired
    UserRegistrationRepository UserRegistrationRepository;

    @Autowired
    EmailService emailService;
    @Autowired
    TokenUtility tokenUtility;

    @Override
    public OrderModule placeOrder(OrderDTO orderDTO) {
        ArrayList<BookModule> bookList = new ArrayList<>();
        UserRegistrationModule userData = iUserService.getUserById(tokenUtility.createToken(orderDTO.getUserId()));
        List<Integer> bookIdList = orderDTO.bookId;
        List<Integer> quantity = orderDTO.quantity;
        int totalPrice = 0;
        for (int i = 0; i < bookIdList.size(); i++) {
            bookList.add(iBookService.getBookById(bookIdList.get(i)));
            totalPrice += iBookService.getBookById(bookIdList.get(i)).getPrice()* (quantity.get(i));
        }
        List<String> nameList = bookList.stream().map(BookModule::getBookName).collect(Collectors.toList());
        OrderModule order = new OrderModule(userData, orderDTO.getBookId() ,orderDTO.address, orderDTO.quantity);
        emailService.sendEmail(userData.getEmailId(), "Order Created Successfully!!", "Order placed for books" + nameList+"Total Price for it is "+totalPrice);
        return orderRepository.save(order);
    }

    @Override
    public OrderModule getOrderID(Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> new BookStoreExceptionHandler("Book  with id " + id + " does not exist in database..!"));
    }

    @Override
    public List<OrderModule> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderModule cancelOrder(int id) {
        OrderModule order = getOrderID(id);
        order.setCancel(false);
        return order;
    }

}
