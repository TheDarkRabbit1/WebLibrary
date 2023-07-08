package com.example.weblibrary.data.book.bookcategory;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookCategoryRowMapper implements RowMapper<BookCategory> {

    @Override
    public BookCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookCategory bookCategory = new BookCategory();
        bookCategory.setId(rs.getLong("id"));
        bookCategory.setName(rs.getString("name"));
        return bookCategory;
    }
}
