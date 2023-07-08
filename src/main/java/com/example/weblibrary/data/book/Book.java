package com.example.weblibrary.data.book;

import com.example.weblibrary.data.book.bookcategory.BookCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {
    private Long id;
    private String name;
    private String author;
    private String description;
    private BookCategory bookCategory;
}
