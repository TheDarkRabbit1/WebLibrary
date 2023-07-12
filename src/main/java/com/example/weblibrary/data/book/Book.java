package com.example.weblibrary.data.book;

import com.example.weblibrary.data.book.bookcategory.BookCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {
    private Long id;
    @NotBlank(message = "Book title should be populated")
    private String name;
    @NotBlank(message = "Author name should be populated")
    private String author;
    @Size(min = 20, max = 255, message = "description should be between 20 and 255 chars")
    private String description;
    private BookCategory bookCategory;
}
