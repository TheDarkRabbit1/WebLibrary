package com.example.weblibrary.data.book;

import com.example.weblibrary.utils.SqlQueries;

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
        return jdbcTemplate.query(SqlQueries.getBooks, new BookRowMapper());
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        return jdbcTemplate.query(SqlQueries.getBookById,new BookRowMapper(),id).stream().findFirst();
    }

    @Override
    public Optional<Book> findBookByNameAndAuthor(String name, String author) {
        return jdbcTemplate.query(SqlQueries.getBookByNameAndAuthor,new BookRowMapper(),name,author).stream().findFirst();
    }

    @Override
    public Long insertBook(Book book) {
        return (long) jdbcTemplate.update(SqlQueries.insertBook,
                book.getName(),
                book.getAuthor(),
                book.getDescription(),
                book.getBookCategory().getId()
                );
    }

    @Override
    public void updateBook(Book book) {
        jdbcTemplate.update(SqlQueries.updateBookInfo,
                book.getName(),
                book.getAuthor(),
                book.getDescription(),
                book.getId());
    }

    @Override
    public void deleteBookById(Long id) {
        jdbcTemplate.update(SqlQueries.deleteBookById,id);
    }

    @Override
    public void deleteAllBooks() {
        jdbcTemplate.update(SqlQueries.deleteBooks);
    }
}
