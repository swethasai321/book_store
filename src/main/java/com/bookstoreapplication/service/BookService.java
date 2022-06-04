package com.bookstoreapplication.service;

import com.bookstoreapplication.dto.BookDTO;
import com.bookstoreapplication.exception.BookStoreExceptionHandler;
import com.bookstoreapplication.module.BookModule;
import com.bookstoreapplication.repository.BookStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService{
    @Autowired
    BookStoreRepository bookStoreRepository;
    @Override
    public Object addBook(BookModule newBookModule) {
        BookModule bookModule = new BookModule(newBookModule);
        bookStoreRepository.save(bookModule);
        return bookModule;
    }

    @Override
    public BookModule getBookById(int bookId){
        return bookStoreRepository.findById(bookId).orElseThrow(() -> new BookStoreExceptionHandler("Book  with id " + bookId + " does not exist in database..!"));

    }

    @Override
    public Object searchAll() {
        return bookStoreRepository.findAll();
    }

    @Override
    public Object update(Integer id, BookDTO bookDTO) {
        if (bookStoreRepository.findById(id).isPresent()) {
            BookModule newBookModule = new BookModule(id, bookDTO);
            BookModule search = bookStoreRepository.save(newBookModule);
            return "Done " + search;
        }
        throw (new BookStoreExceptionHandler("Record not Found"));
    }

    @Override
    public Object removeById(Integer id) {
        Optional<BookModule> newBookModule = bookStoreRepository.findById(id);
        if (newBookModule.isPresent()){
            bookStoreRepository.delete(newBookModule.get());
            return "Record is deleted with id ";
        }
        throw (new BookStoreExceptionHandler("Record not Found"));
    }
    public List<BookModule> findBookByName(String bookName) {
        List<BookModule> book = bookStoreRepository.findBookByName(bookName);

        if (book.size() != 0) {
            return book;

        } throw (new BookStoreExceptionHandler("Record not Found"));
    }

    @Override
    public Object sortByBookName() {
        return bookStoreRepository.sortByBookName();
    }

    @Override
    public Object sortAscByBookPrice() {
        return bookStoreRepository.sortAscByBookPrice();
    }
}