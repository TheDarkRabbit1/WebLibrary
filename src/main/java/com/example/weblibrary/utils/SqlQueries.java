package com.example.weblibrary.utils;

public class SqlQueries {
    public final static String getBookById = "SELECT * FROM book JOIN book_category on book_category.id = book.bookcategory_id WHERE book.id=?";
    public final static String getBooks = "SELECT * FROM book JOIN book_category on book_category.id = book.bookcategory_id";
    public final static String insertBook = "INSERT INTO book (name, author, description, bookCategory_id) VALUES (?, ?, ?, ?)";
    public final static String getBooksByCategoryId = "SELECT * from book JOIN book_category on book_category.id = book.bookcategory_id WHERE book.bookcategory_id=?";
    public final static String getBooksByCategoryName = "SELECT * from book JOIN book_category on book_category.id = book.bookcategory_id WHERE book_category.name=?";
    public final static String getBookByNameAndAuthor = "SELECT * from book JOIN book_category on book_category.id = book.bookcategory_id WHERE book.name=? and book.author=?";

    public final static String deleteBookById = "DELETE FROM book where book.id=?";
    public final static String deleteBooks = "DELETE FROM book";


    public final static String getBookCategoryById="select * from book_category where id=?";
    public final static String getBookCategoryByName="select * from book_category where name=?";
    public final static String getBookCategories="select * from book_category";
    public final static String insertBookCategory="insert into book_category (name) Values(?)";
    public final static String deleteBookCategoryById="delete from book_category where book_category.id=?";
    public final static String deleteBookCategories="delete from book_category";
}
