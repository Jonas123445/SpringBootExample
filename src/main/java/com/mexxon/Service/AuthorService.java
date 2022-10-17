package com.mexxon.Service;

import com.mexxon.Entities.Author;
import com.mexxon.Entities.Book;
import com.mexxon.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

   public Author addAuthor(Author author) {
        return authorRepository.save(author);
   }

   public List<Author> getAuthorList(Author author) {
        return authorRepository.findAll();
   }

}
