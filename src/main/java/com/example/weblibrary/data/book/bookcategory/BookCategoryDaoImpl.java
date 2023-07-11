package com.example.weblibrary.data.book.bookcategory;


import com.example.weblibrary.utils.SqlQueries;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookCategoryDaoImpl implements BookCategoryDao{
    private final JdbcTemplate jdbcTemplate;

    public BookCategoryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BookCategory> findBookCategories() {
        return jdbcTemplate.query(SqlQueries.getBookCategories, new BookCategoryRowMapper());
    }

    @Override
    public Optional<BookCategory> findBookCategoryById(Long id) {
        return jdbcTemplate.query(SqlQueries.getBookCategoryById, new BookCategoryRowMapper(), id).stream().findFirst();
    }

    @Override
    public Optional<BookCategory> findBookCategoryByName(String name) {
        return  jdbcTemplate.query(SqlQueries.getBookCategoryByName, new BookCategoryRowMapper(),name).stream().findFirst();
    }

    @Override
    public Long insertBook(BookCategory bookCategory) {
        jdbcTemplate.update(SqlQueries.insertBookCategory,
                bookCategory.getName());
        return findBookCategoryByName(bookCategory.getName()).orElseThrow().getId();
    }

    @Override
    public void deleteBookById(Long id) {
        jdbcTemplate.update(SqlQueries.deleteBookCategoryById, id);
    }

    @Override
    public void deleteAllBooks() {
        jdbcTemplate.update(SqlQueries.deleteBookCategories);
    }
}
