package com.example.weblibrary.data.book;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {
    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> findBooks() {
        String sql = """
                SELECT book.*, book_category.name as book_category_name, book_category.id as book_category_id 
                FROM book 
                JOIN book_category on book_category.id = book.bookcategory_id
                """;
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        String sql = """
                SELECT book.*, book_category.name as book_category_name, book_category.id as book_category_id 
                FROM book 
                JOIN book_category on book_category.id = book.bookcategory_id 
                WHERE book.id=?
                """;
        return jdbcTemplate.query(sql, new BookRowMapper(), id).stream().findFirst();
    }

    @Override
    public Optional<Book> findBookByNameAndAuthor(String title, String author) {
        String sql = """
                SELECT book.*, book_category.name as book_category_name, book_category.id as book_category_id 
                FROM book 
                JOIN book_category on book_category.id = book.bookcategory_id 
                WHERE book.title=? and book.author=?
                """;
        return jdbcTemplate.query(sql, new BookRowMapper(), title, author).stream().findFirst();
    }

    @Override
    public Long insertBook(Book book, Long bookCategoryId) {
        String sql = """
                INSERT INTO book (title, author, description, bookCategory_id) VALUES (?, ?, ?, ?)
                """;
        jdbcTemplate.update(sql,
                book.getTitle(),
                book.getAuthor(),
                book.getDescription(),
                bookCategoryId
        );
        return findBookByNameAndAuthor(book.getTitle(), book.getAuthor()).orElseThrow().getId();
    }

    @Override
    public void updateBook(Book book) {
        String sql = """
                UPDATE book SET title=?, author=?, description=?, bookCategory_id=? 
                WHERE id=?
                """;
        jdbcTemplate.update(sql,
                book.getTitle(),
                book.getAuthor(),
                book.getDescription(),
                book.getBookCategory().getId(),
                book.getId());
    }

    @Override
    public void deleteBookById(Long id) {
        String sql = """
                DELETE FROM book where book.id=?
                """;
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void deleteAllBooks() {
        String sql = """
                DELETE FROM book
                """;
        jdbcTemplate.update(sql);
    }

    @Override
    public List<Book> findBooksByParams(String title, String author, Long categoryId) {
        String sql = """
                SELECT book.*, book_category.name as book_category_name, book_category.id as book_category_id 
                FROM book 
                JOIN book_category on book_category.id = book.bookcategory_id 
                WHERE (? IS NULL OR LOWER(book.title) LIKE LOWER(?)) AND (? IS NULL OR LOWER(book.author) LIKE LOWER(?)) AND ((?::bigint IS NULL) OR (book_category.id = ?))
                            
                    """;
        Object[] args = new Object[]{title, "%" + title + "%", author, "%" + author + "%", categoryId, categoryId};
        return jdbcTemplate.query(sql, args, new BookRowMapper());
    }

}
