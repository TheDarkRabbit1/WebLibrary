package com.example.weblibrary.data.book.bookcategory;


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
        String sql = """
                select * from book_category
                """;
        return jdbcTemplate.query(sql, new BookCategoryRowMapper());
    }

    @Override
    public Optional<BookCategory> findBookCategoryById(Long id) {
        String sql= """
                select * from book_category where id=?
                """;
        return jdbcTemplate.query(sql, new BookCategoryRowMapper(), id).stream().findFirst();
    }

    @Override
    public Optional<BookCategory> findBookCategoryByName(String name) {
        String sql = """
                select * from book_category where name=?
                """;
        return  jdbcTemplate.query(sql, new BookCategoryRowMapper(),name).stream().findFirst();
    }

    @Override
    public Long insertBookCategory(BookCategory bookCategory) {
        String sql = """
                insert into book_category (name) Values(?)
                """;
        jdbcTemplate.update(sql, bookCategory.getName());
        return findBookCategoryByName(bookCategory.getName()).orElseThrow().getId();
    }

    @Override
    public void deleteBookById(Long id) {
        String sql = """
                delete from book_category where book_category.id=?
                """;
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void deleteAllBooks() {
        String sql = """
                delete from book_category
                """;
        jdbcTemplate.update(sql);
    }
}
