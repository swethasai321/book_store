package com.bookstoreapplication.service;

import com.bookstoreapplication.dto.BookDTO;
import com.bookstoreapplication.dto.CartDTO;
import com.bookstoreapplication.module.CartModule;

public interface ICartService {
//    Object saveBooking(CartModule cartModule);

    CartModule addCart(CartDTO cartDTO);

    Object getCartById(Integer id);

    Object getAllCartItems();

    Object removeById(Integer id);

    Object update(Integer id, int quantity);
}