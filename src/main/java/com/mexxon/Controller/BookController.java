package com.mexxon.Controller;


import com.mexxon.Entities.Author;
import com.mexxon.Entities.Book;
import com.mexxon.Service.AuthorService;
import com.mexxon.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.AttributedString;
import java.util.List;



@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private final BookService bookService;
    @Autowired
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/createauthor")
    public String createAuthor(Model model){
        model.addAttribute("newAuthor", new Author());
        return "addauthor";
    }


    @GetMapping("/allauthors")
    public String getAllAuthors(Model model){
        List<Author> listAuthor = authorService.getAuthorList();
        model.addAttribute("listAuthor", listAuthor);
        return "booklist";
    }

    @PostMapping("/addauthor")
    public String addAuthor( Author author){
         authorService.addAuthor(author);

        return "addbook";
    }


    @GetMapping("/createbook")
    public String createBook(Model model){
        model.addAttribute("newBook", new Book());
        return "addbook";
    }

    @GetMapping("/allbooks")
    public String getAllBooks(Model model){
        List<Book> listBook = bookService.getBookList();
        model.addAttribute("listBook", listBook);
        return "booklist";
    }


// Add a Book
    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book,Model model){
        Book newBook = bookService.addBook(book);
        model.addAttribute("newBook", newBook);
        return "booklist";
    }


}
