package com.bookstoreapplication.service;
import com.bookstoreapplication.dto.WishListDTO;
import com.bookstoreapplication.module.WishList;

import java.util.List;

public interface IWishListService {

    WishList addItem(WishListDTO wishListDTO);

    String update(Integer id, WishListDTO wishListDTO);

    List<WishList> searchAll();

    WishList getItemById(int userId);
    String removeById(Integer id);
}
