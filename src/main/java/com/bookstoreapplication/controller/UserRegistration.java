package com.bookstoreapplication.controller;

import com.bookstoreapplication.dto.LoginDTO;
import com.bookstoreapplication.dto.ResponseDTO;
import com.bookstoreapplication.dto.UserDTO;
import com.bookstoreapplication.exception.BookStoreExceptionHandler;
import com.bookstoreapplication.module.UserRegistrationModule;
import com.bookstoreapplication.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(("/user"))
public class UserRegistration {

    @Autowired
    IUserService iUserService;

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello BookStore";
    }

    /*--------------------Post Operation-------------------*/
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addperson(@RequestBody UserDTO userDTO){
        UserRegistrationModule newUserRegistrationModule = new UserRegistrationModule(userDTO);
        ResponseDTO responseDTO = new ResponseDTO("Add record  Success", iUserService.addPerson(newUserRegistrationModule));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.CREATED);
    }
    //-----------------------------------------Update-------------------------------
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> editData(@PathVariable Integer id,@RequestBody UserDTO userDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Successfully updated",iUserService.update(id,userDTO));
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }



    //----------------------------------------Get-all------------------------------
    @GetMapping("/get-all")
    public ResponseEntity<ResponseDTO> getAll(){
        ResponseDTO responseDTO = new ResponseDTO("Getting all the record..", iUserService.searchAll());
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }



    @PostMapping("/userlogin")
    public String userLogin(@RequestBody LoginDTO loginDTO) {
        String login = iUserService.userLogin(loginDTO);
        if (login != null) {
            ResponseDTO respnseDTO = new ResponseDTO("LOGIN SUCCESSFUL", login);
            return "LOGIN SUCCESSFUL";
        } else {
            ResponseDTO respnseDTO = new ResponseDTO("User login not successfully", login);
            throw (new BookStoreExceptionHandler("Record not Found"));
        }
    }
}