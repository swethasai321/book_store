package com.bookstoreapplication.controller;

import com.bookstoreapplication.dto.BookDTO;
import com.bookstoreapplication.dto.ResponseDTO;
import com.bookstoreapplication.module.BookModule;
import com.bookstoreapplication.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    IBookService iBookService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello Bridgelabz";
    }

    /*--------------------Post Operation-------------------*/
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addBook(@RequestBody BookDTO bookDTO){
        BookModule newBookModule = new BookModule(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Add record  Success", iBookService.addBook(newBookModule));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.CREATED);
    }

    //----------------------------------------get-by-Id---------------------------
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getAddressById(@PathVariable Integer id) {
        ResponseDTO responseDTO = new ResponseDTO("Record found successfully", iBookService.getBookById(id));
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.CREATED);
    }
    //----------------------------------------get-by-Book---------------------------
    @GetMapping("/get-book/{bookName}")
    public ResponseEntity<ResponseDTO> getBookByName(@PathVariable("bookName") String bookName){
        ResponseDTO responseDTO = new ResponseDTO("Get Call for Name successful",iBookService.findBookByName(bookName));
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
    //----------------------------------------Get-all------------------------------
    @GetMapping("/get-all")
    public ResponseEntity<ResponseDTO> getAddress(){
        ResponseDTO responseDTO = new ResponseDTO("Getting all the record..", iBookService.searchAll());
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }
    //-----------------------------------------Update-------------------------------
    @PutMapping("/edit/{id}")
    public ResponseEntity<ResponseDTO> editData(@PathVariable Integer id,@RequestBody BookDTO bookDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Successfully updated",iBookService.update(id,bookDTO));
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }

    //---------------------------------------Delete---------------------------------
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteAddress(@PathVariable Integer id){
        ResponseDTO responseDTO = new ResponseDTO("Delete Operation Successful", iBookService.removeById(id));
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }
    //---------------------------------------Sorting---------------------------------
    @GetMapping(value = "/sort")
    public ResponseEntity<ResponseDTO> sortAscByBookName(){
        ResponseDTO responseDTO = new ResponseDTO("Sorting The records by Name", iBookService.sortByBookName());
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }
    @GetMapping(value = "/sortprice")
    public ResponseEntity<ResponseDTO> sortAscByBookPrice(){
        ResponseDTO responseDTO = new ResponseDTO("Sorting The records by price", iBookService.sortAscByBookPrice());
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }
}