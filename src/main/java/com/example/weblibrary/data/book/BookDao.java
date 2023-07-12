package com.example.weblibrary.data.book;

import java.util.List;
import java.util.Optional;
public interface BookDao {
    List<Book> findBooks();
    Optional<Book> findBookById(Long id);
    Optional<Book> findBookByNameAndAuthor(String name, String author);
    Long insertBook(Book book, Long bookCategoryId);
    void updateBook(Book book);

    void deleteBookById(Long id);

    void deleteAllBooks();

    List<Book> findBooksByParams(String title, String author, Long categoryId);
}
