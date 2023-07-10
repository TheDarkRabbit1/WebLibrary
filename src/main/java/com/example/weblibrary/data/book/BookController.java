package com.example.weblibrary.data.book;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;
    @GetMapping("")
    public String bookList(Model model){
        model.addAttribute("librarian",true);
        model.addAttribute("books",bookService.getAllBooks());
        model.addAttribute("categories",bookService.getAllBookCategories());
        return "/book/booksPage";
    }
    @GetMapping("/bookForm")
    public String displayEmptyBookForm(Model model,
                                       @RequestParam(value = "bookId", required = false) Optional<Long> bookId){
        if (bookId.isPresent()){
            model.addAttribute("book",bookService.getBookById(bookId.get()));
        }else{
            model.addAttribute("book",new Book());
        }
        model.addAttribute("categories",bookService.getAllBookCategories());
        return "/book/bookForm";
    }
    @PostMapping("/add")
    public String addNewBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult,
                             Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("categories",bookService.getAllBookCategories());
            return "/book/bookForm";
        }
        if (book.getId()==null){
            bookService.insertBook(book);
        } else {
            Book existingBook = bookService.getBookById(book.getId());
            existingBook.setName(book.getName());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setDescription(book.getDescription());
            bookService.updateBook(existingBook);
        }
        return "redirect:/books";
    }
}
