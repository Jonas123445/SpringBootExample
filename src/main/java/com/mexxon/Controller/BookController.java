package com.mexxon.Controller;

import com.mexxon.Repository.AuthorRepository;
import com.mexxon.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

@Autowired
    BookRepository bookRepository;

@Autowired
    AuthorRepository authorRepository;

    @GetMapping("/")
    public String defaultstart (Model model) {

        return "redirect:/books";
    }

        @GetMapping("/books")
    public String books(Model model){
        model.addAttribute("beispieltext", "Das ist ein Beispieltext");
        // thymeleaf Doku:
        // https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html



        return "booklist";
    }

}
