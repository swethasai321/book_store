package com.bookstoreapplication.exception;

public class BookStoreExceptionHandler extends RuntimeException{
    public BookStoreExceptionHandler(String message){
        super(message);
    }
}