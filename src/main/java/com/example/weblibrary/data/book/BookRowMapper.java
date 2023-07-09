package com.example.weblibrary.data.book;

import com.example.weblibrary.data.book.bookcategory.BookCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setDescription(rs.getString("description"));

        BookCategory bookCategory = new BookCategory();
        bookCategory.setId(rs.getLong("book_category_id"));
        bookCategory.setName(rs.getString("book_category_name"));
        book.setBookCategory(bookCategory);

        return book;
    }
}

