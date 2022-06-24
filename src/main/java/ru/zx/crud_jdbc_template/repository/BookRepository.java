package ru.zx.crud_jdbc_template.repository;

import ru.zx.crud_jdbc_template.entity.Book;

import java.util.List;

public interface BookRepository {
    Book save(Book book);
    List<Book> bookList();
    List<Book> bookListInId(Long id);
}
