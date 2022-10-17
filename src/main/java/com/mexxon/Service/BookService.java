package com.mexxon.Service;

import com.mexxon.Entities.Book;
import com.mexxon.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
       return bookRepository.save(book);
    }

    public List<Book> getBookList (){
        return bookRepository.findAll();
    }
}
