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




  /*  @PostMapping("/addauthor")
    public String addAuthor( Author author,Model model){
         authorService.addAuthor(author);
        model.addAttribute("authorID",author.getId());
        return "addbook";
    }

   */

    @PostMapping("/addauthor")
    public String addAuthor( Author author,Model model){
        authorService.addAuthor(author);

        Book newBook = new Book();
        newBook.setAuthor(author);

        model.addAttribute("newBook", newBook);

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
    public String addBook(Book book){

        bookService.addBook(book);



        return "addbook";
    }



 /*   @PostMapping("/addbook")
    public ResponseEntity<Book> addBook(Book book){

       Book newBook= bookService.addBook(book);



        return new ResponseEntity<>(newBook,HttpStatus.OK);
    }

   */

    @GetMapping("/allauthors")
    public String getAllAuthors(Model model){
        List<Author> listAuthor = authorService.getAuthorList();
        model.addAttribute("listAuthor", listAuthor);
        return "booklist";
    }

}
