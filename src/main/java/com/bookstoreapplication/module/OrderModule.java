package com.bookstoreapplication.module;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * The @Entity annotation specifies that the class is an entity and is mapped to a database table
 */
@Entity
@Data
@Table(name="orderDetails")
public class OrderModule {
    @Id
    @GeneratedValue
    private Integer orderID;
    private LocalDate date = LocalDate.now();
    private Integer price;
    private Integer quantity;
    private String address;
    @ManyToOne
    @JoinColumn(name="userId")
    private UserRegistrationModule user;
    @ManyToOne
    @JoinColumn(name="bookId")
    private BookModule book;
    private boolean cancel;

    public OrderModule(Integer orderID, Integer price, Integer quantity, String address, BookModule book, UserRegistrationModule user, boolean cancel) {
        this.orderID = orderID;
        this.price=price;
        this.quantity=quantity;
        this.address=address;
        this.book = book;
        this.user=user;
        this.cancel = cancel;
    }

    public OrderModule() {
        super();
    }

    public OrderModule(Integer price, Integer quantity, String address, BookModule book, UserRegistrationModule user, boolean cancel) {
        this.price=price;
        this.quantity=quantity;
        this.address=address;
        this.book = book;
        this.user=user;
        this.cancel = cancel;
    }

    public OrderModule(UserRegistrationModule userData, List<Integer> bookId, String address, List<Integer> quantity) {
    }
}
