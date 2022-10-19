package com.mexxon.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "author")
public class Author implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nachname;
    private String vorname;


    @OneToMany(mappedBy = "author")
    @LazyCollection(LazyCollectionOption.TRUE)
    @JsonIgnore // fix the infinite recursion
    private List<Book> books ;

}
