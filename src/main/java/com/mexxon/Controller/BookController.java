package com.mexxon.Controller;


import com.mexxon.Entities.Author;
import com.mexxon.Entities.Book;

import com.mexxon.Service.AuthorService;
import com.mexxon.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }


    @GetMapping("/allauthors")
    public ResponseEntity<List<Author>> getAllAuthors(){
        List<Author> listAuthor = authorService.getAuthorList();
        return new ResponseEntity<>(listAuthor, HttpStatus.OK);
    }
    @PostMapping("/addauthor")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author){
        Author newAuthor = authorService.addAuthor(author);
        return new ResponseEntity<>(newAuthor, HttpStatus.OK);
    }

    @GetMapping("/allbooks")
    public ResponseEntity<List<Book>> getAllBooks(Model model){
        List<Book> listBook = bookService.getBookList();
        model.addAttribute("listBook", listBook);
        return new ResponseEntity<>(listBook, HttpStatus.OK);
    }


// Add a Book
    @PostMapping("/addbook")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book newBook = bookService.addBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.OK);
    }


}
