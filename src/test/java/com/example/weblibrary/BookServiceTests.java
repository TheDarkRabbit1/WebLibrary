package com.example.weblibrary;

import com.example.weblibrary.data.book.Book;
import com.example.weblibrary.data.book.BookDao;
import com.example.weblibrary.data.book.BookService;
import com.example.weblibrary.data.book.bookcategory.BookCategory;
import com.example.weblibrary.data.book.bookcategory.BookCategoryDao;
import com.example.weblibrary.exceptions.EntityNotFoundException;
import com.example.weblibrary.exceptions.InsertionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BookServiceTests {
    @Mock
    private BookDao bookDao;
    @Mock
    private BookCategoryDao bookCategoryDao;
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bookService = new BookService(bookDao, bookCategoryDao);
    }

    @Test
    public void testGetBookById() {
        Book expectedBook = new Book();
        expectedBook.setId(1L);
        expectedBook.setName("Test Book");
        when(bookDao.findBookById(1L)).thenReturn(Optional.of(expectedBook));
        Book actualBook = bookService.getBookById(1L);
        assertEquals(expectedBook, actualBook);
    }

    @Test
    public void testGetBookByIdNotFound() {
        when(bookDao.findBookById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> bookService.getBookById(1L));
    }

    @Test
    public void testGetAllBooks() {
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book());
        expectedBooks.add(new Book());
        when(bookDao.findBooks()).thenReturn(expectedBooks);
        List<Book> actualBooks = bookService.getAllBooks();
        assertEquals(expectedBooks, actualBooks);
    }


    @Test
    public void testDeleteBookById() {
        doNothing().when(bookDao).deleteBookById(1L);
        when(bookDao.findBookById(1L)).thenReturn(Optional.of(new Book()));
        bookService.deleteBookById(1L);
    }

    @Test
    public void testDeleteBookByIdNotFound() {
        when(bookDao.findBookById(1L)).thenReturn(Optional.empty());
        assertThrows(InsertionException.class, () -> bookService.deleteBookById(1L));
    }

    @Test
    public void testDeleteBooks() {
        doNothing().when(bookDao).deleteAllBooks();
        bookService.deleteBooks();
    }

    @Test
    public void testGetBookCategoryById() {
        BookCategory expectedCategory = new BookCategory();
        expectedCategory.setId(1L);
        expectedCategory.setName("Test Category");
        when(bookCategoryDao.findBookCategoryById(1L)).thenReturn(Optional.of(expectedCategory));
        BookCategory actualCategory = bookService.getBookCategoryById(1L);
        assertEquals(expectedCategory, actualCategory);
    }

    @Test
    public void testGetBookCategoryByIdNotFound() {
        when(bookCategoryDao.findBookCategoryById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> bookService.getBookCategoryById(1L));
    }

    @Test
    public void testGetAllBooksCategories() {
        List<BookCategory> expectedCategories = new ArrayList<>();
        expectedCategories.add(new BookCategory());
        expectedCategories.add(new BookCategory());
        when(bookCategoryDao.findBookCategories()).thenReturn(expectedCategories);
        List<BookCategory> actualCategories = bookService.getAllBookCategories();
        assertEquals(expectedCategories, actualCategories);
    }

    @Test
    public void testInsertBookCategory() {
        BookCategory bookCategory = new BookCategory();
        bookCategory.setName("Test Category");
        when(bookCategoryDao.insertBookCategory(bookCategory)).thenReturn(1L);
        Long id = bookService.insertBookCategory(bookCategory);
        assertEquals(1L, id);
    }

    @Test
    public void testInsertBookCategoryAlreadyExists() {
        BookCategory bookCategory = new BookCategory();
        bookCategory.setName("Test Category");
        when(bookCategoryDao.findBookCategoryByName("Test Category")).thenReturn(Optional.of(bookCategory));
        assertThrows(InsertionException.class, () -> bookService.insertBookCategory(bookCategory));
    }

    @Test
    public void testInsertBookCategoryFailed() {
        BookCategory bookCategory = new BookCategory();
        bookCategory.setName("Test Category");
        when(bookCategoryDao.insertBookCategory(bookCategory)).thenReturn(0L);
        assertThrows(InsertionException.class, () -> bookService.insertBookCategory(bookCategory));
    }

    @Test
    public void testDeleteBookCategoryById() {
        doNothing().when(bookCategoryDao).deleteBookById(1L);
        when(bookCategoryDao.findBookCategoryById(1L)).thenReturn(Optional.of(new BookCategory()));
        bookService.deleteBookCategoryById(1L);
    }

    @Test
    public void testDeleteBookCategoryIdNotFound() {
        when(bookCategoryDao.findBookCategoryById(1L)).thenReturn(Optional.empty());
        assertThrows(InsertionException.class, () -> bookService.deleteBookCategoryById(1L));
    }

    @Test
    public void testDeleteBookCategories() {
        doNothing().when(bookCategoryDao).deleteAllBooks();
        bookService.deleteBookCategories();
    }
}
