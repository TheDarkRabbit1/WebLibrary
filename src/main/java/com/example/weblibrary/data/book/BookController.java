package com.example.weblibrary.data.book;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;
    @GetMapping("")
    public String bookList(Model model){
        model.addAttribute("books",bookService.getAllBooks());
        return "/book/bookList";
    }

}
