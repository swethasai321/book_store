package com.bookstoreapplication.dto;

import com.bookstoreapplication.module.BookModule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {


    public int bookId;
    public int userId;
    public int quantity;
}