package com.example.weblibrary.data.book.bookcategory;

import java.util.List;
import java.util.Optional;

public interface BookCategoryDao {
    List<BookCategory> findBookCategories();
    Optional<BookCategory> findBookCategoryById(Long id);
    Long insertBook(BookCategory bookCategory);
    void deleteBookById(Long id);
    void deleteAllBooks();
}
