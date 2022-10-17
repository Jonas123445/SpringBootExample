package com.mexxon.Repository;


import com.mexxon.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorRepository extends JpaRepository<Author,Integer> {


}
