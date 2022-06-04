package com.bookstoreapplication.controller;

import com.bookstoreapplication.dto.BookDTO;
import com.bookstoreapplication.dto.CartDTO;
import com.bookstoreapplication.dto.ResponseDTO;
import com.bookstoreapplication.dto.ResponseDTO;
import com.bookstoreapplication.module.CartModule;
import com.bookstoreapplication.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ICartService iCartService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addCart(@RequestBody CartDTO cartDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Cart Item add Successfull", iCartService.addCart(cartDTO));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.CREATED);
    }
    //----------------------------------------get-by-Id---------------------------
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getAddressById(@PathVariable Integer id) {
        ResponseDTO responseDTO = new ResponseDTO("Record found successfully", iCartService.getCartById(id));
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.CREATED);
    }

    //----------------------------------------Get-all------------------------------
    @GetMapping("/get-all")
    public ResponseEntity<ResponseDTO> getAllCartItems(){
        ResponseDTO responseDTO = new ResponseDTO("Showing all the records..", iCartService.getAllCartItems());
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }

    //-----------------------------------------Update-------------------------------
    @PutMapping("/edit/{id}")
    public ResponseEntity<ResponseDTO> editData(@PathVariable Integer id,@RequestBody  int quantity) {
        ResponseDTO responseDTO = new ResponseDTO("Successfully updated",iCartService.update(id,quantity));
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteAddress(@PathVariable Integer id){
        ResponseDTO responseDTO = new ResponseDTO("Delete Operation Successfull", iCartService.removeById(id));
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }
}