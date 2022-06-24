package ru.zx.crud_jdbc_template.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.zx.crud_jdbc_template.UnitTestBase;
import ru.zx.crud_jdbc_template.entity.Author;
import ru.zx.crud_jdbc_template.entity.Book;
import ru.zx.crud_jdbc_template.faker.Fake;
import ru.zx.crud_jdbc_template.repository.AuthorRepository;
import ru.zx.crud_jdbc_template.repository.BookRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class BookRepositoryTest extends UnitTestBase {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    Fake fake;

    Book saveBook;

    @BeforeEach
    @Transactional
    void saveInit() {
        Author saveAuthor = authorRepository.save(fake.getFakeAuthor());
        Book book = fake.getFakeBook();
        book.setAuthor(saveAuthor);
        saveBook = bookRepository.save(book);
    }


    @Test
    void save() {
        Author saveAuthor = authorRepository.save(fake.getFakeAuthor());
        Book book = fake.getFakeBook();
        book.setAuthor(saveAuthor);
        Book saveBook = bookRepository.save(book);
        assertNotNull(saveBook);
    }

    @Test
    void bookList() {
        List<Book> books = bookRepository.bookList();
        assertFalse(books.isEmpty());
    }

    @Test
    void bookListId() {
        List<Book> books = bookRepository.bookListInId(saveBook.getId());
        assertFalse(books.isEmpty());
    }

    @Test
    void bookListInId() {
    }
}
