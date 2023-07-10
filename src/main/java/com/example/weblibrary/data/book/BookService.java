package com.example.weblibrary.data.book;

import com.example.weblibrary.data.book.bookcategory.BookCategory;
import com.example.weblibrary.data.book.bookcategory.BookCategoryDao;
import com.example.weblibrary.exceptions.EntityNotFoundException;
import com.example.weblibrary.exceptions.InsertionException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class BookService {
    private final BookDao bookDao;
    private final BookCategoryDao bookCategoryDao;

    public Book getBookById(Long id){
        return bookDao.findBookById(id)
                .orElseThrow(()->new EntityNotFoundException(String.format("book of %s id wasn't found",id)));
    }
    public List<Book> getAllBooks (){
        return bookDao.findBooks();
    }
    public Long insertBook(Book book){
        bookDao.findBookByNameAndAuthor(book.getName(),book.getAuthor()).ifPresent(b->{
            throw new InsertionException("Book with this name already exist");
        });
        Long id = bookDao.insertBook(book);
        if (id<=0)
            throw new InsertionException("failed to insert book");
        return id;
    }
    public void deleteBookById(Long id) {
        bookDao.findBookById(id)
                .ifPresentOrElse(
                        b -> bookDao.deleteBookById(id),
                        () -> {
                            throw new InsertionException(String.format("no book of %s id was found", id));
                        });
    }

    public void deleteBooks(){
        bookDao.deleteAllBooks();
    }


    public BookCategory getBookCategoryById(Long id){
        return bookCategoryDao.findBookCategoryById(id)
                .orElseThrow(()->new EntityNotFoundException(String.format("book category of %s id wasn't found",id)));
    }

    public List<BookCategory> getAllBookCategories(){
        return bookCategoryDao.findBookCategories();
    }
    public Long insertBookCategory(BookCategory bookCategory) {
        bookCategoryDao.findBookCategoryByName(bookCategory.getName())
                .ifPresent(bc -> {
                    throw new InsertionException("Category with this name already exists");
                });
        Long id = bookCategoryDao.insertBook(bookCategory);
        if (id <= 0)
            throw new InsertionException("failed to insert book category");
        return id;
    }

    public void deleteBookCategoryById(Long id) {
        bookCategoryDao.findBookCategoryById(id)
                .ifPresentOrElse(
                        bc -> bookCategoryDao.deleteBookById(id),
                        () -> {
                            throw new InsertionException(String.format("no book category of %s id was found", id));
                        });
    }

    public void deleteBookCategories(){
        bookCategoryDao.deleteAllBooks();
    }

    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }
}
