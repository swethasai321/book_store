package com.bookstoreapplication.dto;

import com.bookstoreapplication.module.BookModule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.ElementCollection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    public String bookName;
    public int price;
    public String authorName;
    @ElementCollection
    public BookModule bookModule;
    public int bookQuantity;
}