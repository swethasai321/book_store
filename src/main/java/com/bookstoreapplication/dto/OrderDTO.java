package com.bookstoreapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    public String address;

    public List<Integer> bookId;

    public int userId;

    public List<Integer> quantity;

}