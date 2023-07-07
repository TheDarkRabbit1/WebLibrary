package com.example.weblibrary.data.book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    List<Book> findBooks();
    Optional<Book> findBookById();
    void deleteBookById();
    void deleteAllBooks();
}
