package com.bookstoreapplication.service;

import com.bookstoreapplication.controller.UserRegistration;
import com.bookstoreapplication.dto.BookDTO;
import com.bookstoreapplication.dto.CartDTO;
import com.bookstoreapplication.exception.BookStoreExceptionHandler;
import com.bookstoreapplication.module.BookModule;
import com.bookstoreapplication.module.CartModule;
import com.bookstoreapplication.module.UserRegistrationModule;
import com.bookstoreapplication.repository.CartRepository;
import com.bookstoreapplication.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService implements ICartService{

    @Autowired
    CartRepository cartRepository;
    @Autowired
    IUserService iUserService;
    @Autowired
    IBookService iBookService;

    @Autowired
    TokenUtility tokenUtility;
    @Override
    public CartModule addCart(CartDTO cartDTO) {
        UserRegistrationModule user = iUserService.getUserById(tokenUtility.createToken(cartDTO.getUserId()));
        BookModule book = iBookService.getBookById(cartDTO.getBookId());
        CartModule cart = new CartModule(user,book, cartDTO.quantity);
        return cartRepository.save(cart);
    }

    @Override
    public Object getCartById(Integer id) {
        return cartRepository.findById(id).orElseThrow(() -> new BookStoreExceptionHandler("Book  with id " + id + " does not exist in database..!"));

    }

    @Override
    public Object getAllCartItems() {
        return cartRepository.findAll();
    }

    @Override
    public Object removeById(Integer id) {
        Optional<CartModule> cartModule = cartRepository.findById(id);
        if (cartModule.isPresent()){
            cartRepository.delete(cartModule.get());
            return "Record is deleted with id ";
        }
        throw (new BookStoreExceptionHandler("Record not Found"));
    }

    @Override
    public Object update(Integer id, int quantity) {
        if (cartRepository.findById(id).isPresent()) {
            CartModule cartModule = new CartModule(id, quantity);
            CartModule search = cartRepository.save(cartModule);
            return "Done " + search;
        }
        throw (new BookStoreExceptionHandler("Record not Found"));
    }
}