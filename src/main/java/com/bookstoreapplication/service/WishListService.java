package com.bookstoreapplication.service;

import com.bookstoreapplication.dto.WishListDTO;
import com.bookstoreapplication.exception.BookStoreExceptionHandler;
import com.bookstoreapplication.module.BookModule;
import com.bookstoreapplication.module.UserRegistrationModule;
import com.bookstoreapplication.module.WishList;
import com.bookstoreapplication.repository.BookStoreRepository;
import com.bookstoreapplication.repository.UserRegistrationRepository;
import com.bookstoreapplication.repository.WishListRepository;
import com.bookstoreapplication.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishListService implements IWishListService {
    @Autowired
    WishListRepository wishListRepository;
    @Autowired
    IUserService iUserService;
    @Autowired
    UserRegistrationRepository UserRegistrationRepository;
    @Autowired
    IBookService iBookService;
    @Autowired
    BookStoreRepository bookStoreRepository;
    @Autowired
    TokenUtility tokenUtility;
    @Override
    public WishList addItem(WishListDTO wishListDTO) {
        UserRegistrationModule userRegistrationModule = iUserService.getUserById(tokenUtility.createToken(wishListDTO.userId));
        BookModule bookService = iBookService.getBookById(wishListDTO.getBookId());
        WishList wishList = new WishList(userRegistrationModule, bookService);
        return wishListRepository.save(wishList);

    }

    @Override
    public String update(Integer id, WishListDTO wishListDTO) {
        if (wishListRepository.findById(id).isPresent()) {
            WishList wishList = new WishList(id, wishListDTO);
            WishList search = wishListRepository.save(wishList);
            return "Done " + search;
        }
        throw (new BookStoreExceptionHandler("Record not Found"));
    }

    @Override
    public List<WishList> searchAll() {
        return wishListRepository.findAll();
    }

    @Override
    public WishList getItemById(int wId) {
        return wishListRepository.findById(wId).orElseThrow(() -> new BookStoreExceptionHandler("Book  with id " + wId + " does not exist in database..!"));
    }

    @Override
    public String removeById(Integer id) {
        Optional<WishList> wishList = wishListRepository.findById(id);
        if (wishList.isPresent()){
            wishListRepository.delete(wishList.get());
            return "Record is deleted with id ";
        }
        throw (new BookStoreExceptionHandler("Record not Found"));
    }
}
