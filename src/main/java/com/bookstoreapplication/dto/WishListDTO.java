package com.bookstoreapplication.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class WishListDTO {
    public int userId;
    public int bookId;
}