package com.bookstoreapplication.controller;

import com.bookstoreapplication.dto.OrderDTO;
import com.bookstoreapplication.dto.ResponseDTO;
import com.bookstoreapplication.module.BookModule;
import com.bookstoreapplication.module.OrderModule;
import com.bookstoreapplication.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    IOrderService iOrderService;

    //----------------------------------------Place The Order---------------------------
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> placeOrder(@RequestBody OrderDTO orderDTO){
        ResponseDTO responseDTO = new ResponseDTO("Add record  Success", iOrderService.placeOrder(orderDTO));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.CREATED);
    }

    //----------------------------------------get-by-Id---------------------------
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getOrderById(@PathVariable Integer id) {
        ResponseDTO responseDTO = new ResponseDTO("Record found successfully", iOrderService.getOrderID(id));
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.CREATED);
    }

    //----------------------------------------Get-all------------------------------
    @GetMapping("/get-all")
    public ResponseEntity<ResponseDTO> getAllOrders(){
        ResponseDTO responseDTO = new ResponseDTO("Getting all the record..", iOrderService.getAllOrders());
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }

    //----------------------------------------Cancel The Order---------------------------
    @PutMapping("/cancle/{id}")
    public ResponseEntity<ResponseDTO> cancelOrder(@PathVariable int id){
        ResponseDTO responseDTO = new ResponseDTO("Order Canceled", iOrderService.cancelOrder(id));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.CREATED);
    }
}