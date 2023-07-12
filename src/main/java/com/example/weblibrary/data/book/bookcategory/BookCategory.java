package com.example.weblibrary.data.book.bookcategory;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookCategory {
    private Long id;
    private String name;
}
