package com.example.weblibrary.data.book;

import com.example.weblibrary.data.book.bookcategory.BookCategory;
import com.example.weblibrary.exceptions.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("")
    public String bookList(@RequestParam(value = "title", required = false) Optional<String> title,
                           @RequestParam(value = "author", required = false) Optional<String> author,
                           @RequestParam(value = "category", required = false) Optional<Long> categoryId,
                           Model model) {
        if (title.isEmpty() && author.isEmpty() && categoryId.isEmpty()) {
            model.addAttribute("books", bookService.getAllBooks());
        } else {
            model.addAttribute("books", bookService.getBooksByParams(
                    title.orElse(null),
                    author.orElse(null),
                    categoryId.orElse(null)
            ));
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isLibrarian = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("LIBRARIAN"));
        model.addAttribute("librarian", isLibrarian);
        model.addAttribute("categories", bookService.getAllBookCategories());
        return "book/booksPage";
    }

    @GetMapping("/bookForm")
    public String displayEmptyBookForm(Model model,
                                       @RequestParam(value = "bookId", required = false) Optional<Long> bookId) {
        List<BookCategory> bookCategories = bookService.getAllBookCategories();
        if (bookId.isPresent()) {
            Long id = bookId.get();
            Book book = bookService.getBookById(id);
            if (book != null) {
                model.addAttribute("book", book);
            }
            bookCategories.remove(book.getBookCategory());
            bookCategories.add(0, book.getBookCategory());
        } else {
            model.addAttribute("book", new Book());
        }
        model.addAttribute("categories", bookCategories);
        return "book/bookForm";
    }

    @PostMapping("/add")
    public String addNewBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult,
                             @RequestParam(value = "bookCategoryId", required = false) Optional<Long> bookCategoryId,
                             @RequestParam(value = "newCategory", required = false) Optional<String> newCategory,
                             Model model) {
        if (bindingResult.hasErrors()) {
            List<BookCategory> bookCategories = bookService.getAllBookCategories();
            bookCategories.remove(book.getBookCategory());
            bookCategories.add(0, book.getBookCategory());
            bookCategories.removeIf(Objects::isNull);
            model.addAttribute("book", book);
            model.addAttribute("categories", bookCategories);
            return "book/bookForm";
        }
        if (newCategory.isPresent() && !newCategory.get().isEmpty()) {
            BookCategory bookCategory = new BookCategory();
            bookCategory.setName(newCategory.get());
            bookCategoryId = Optional.of(bookService.insertBookCategory(bookCategory));
        }
        if (bookCategoryId.isEmpty()) {
            throw new EntityNotFoundException("No book Category Id was formed for Insertion");
        }
        if (book.getId() == null) {
            bookService.insertBook(book, bookCategoryId.get());
        } else {
            Book existingBook = bookService.getBookById(book.getId());
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setDescription(book.getDescription());
            existingBook.setBookCategory(bookService.getBookCategoryById(bookCategoryId.get()));
            bookService.updateBook(existingBook);
        }
        return "redirect:/books";
    }

    @PostMapping("/deleteBook")
    public String deleteBook(@ModelAttribute("book") Book book) {
        bookService.deleteBookById(book.getId());
        return "redirect:/books";
    }
}
