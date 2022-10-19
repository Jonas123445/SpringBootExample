package com.mexxon.Service;

import com.mexxon.Entities.Author;
import com.mexxon.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private final AuthorRepository authorRepository;


    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

   public Author addAuthor(Author author) {
        return authorRepository.save(author);
   }

   public List<Author> getAuthorList() {
        return authorRepository.findAll();
   }

}
