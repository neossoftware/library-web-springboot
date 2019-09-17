package com.neosuniversity.demo.repository;

import com.neosuniversity.demo.model.Author;


import java.util.List;
import java.util.Optional;

public interface AuthorDAORepository {

    Author save(Author author);
    Optional<Author> findById(Long id);
    List<Author> findAll();
    long count();
    void delete(Author author);
    void update(Author author);
    boolean existById(Long id);
}
