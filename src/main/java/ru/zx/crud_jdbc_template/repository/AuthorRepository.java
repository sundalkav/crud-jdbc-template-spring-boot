package ru.zx.crud_jdbc_template.repository;

import ru.zx.crud_jdbc_template.entity.Author;
import ru.zx.crud_jdbc_template.entity.Book;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AuthorRepository {
    Author save(Author author);
    Author saveParam(Author author);
    Long saveId(Author author);
    Author update(Author author);
    Optional<Author> findById(Long id);
    List<Author> listAuthor();
    List<Author> listAuthorWithBooks();
    boolean delete(Long id);
}
