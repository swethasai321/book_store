package com.bookstoreapplication.controller;

import com.bookstoreapplication.dto.BookDTO;
import com.bookstoreapplication.dto.ResponseDTO;
import com.bookstoreapplication.dto.WishListDTO;
import com.bookstoreapplication.module.WishList;
import com.bookstoreapplication.service.IWishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    IWishListService iWishListService;

    /*--------------------Post Operation-------------------*/
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addBook(@RequestBody WishListDTO wishListDTO){
        ResponseDTO responseDTO = new ResponseDTO("Add record  Success", iWishListService.addItem(wishListDTO));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.CREATED);
    }

    //----------------------------------------get-by-Id---------------------------
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getOrderById(@PathVariable Integer id) {
        ResponseDTO responseDTO = new ResponseDTO("Record found successfully", iWishListService.getItemById(id));
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.CREATED);
    }

    //----------------------------------------Get-all------------------------------
    @GetMapping("/get-all")
    public ResponseEntity<ResponseDTO> getAllOrders(){
        ResponseDTO responseDTO = new ResponseDTO("Getting all the record..", iWishListService.searchAll());
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }
    //---------------------------------------Delete---------------------------------
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteAddress(@PathVariable Integer id){
        ResponseDTO responseDTO = new ResponseDTO("Delete Operation Successful", iWishListService.removeById(id));
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }
    //-----------------------------------------Update-------------------------------
    @PutMapping("/edit/{id}")
    public ResponseEntity<ResponseDTO> editData(@PathVariable Integer id,@RequestBody WishListDTO wishListDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Successfully updated",iWishListService.update(id,wishListDTO));
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }
}
