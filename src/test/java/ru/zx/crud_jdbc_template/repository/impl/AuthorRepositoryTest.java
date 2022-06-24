package ru.zx.crud_jdbc_template.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.zx.crud_jdbc_template.UnitTestBase;
import ru.zx.crud_jdbc_template.entity.Author;
import ru.zx.crud_jdbc_template.faker.Fake;
import ru.zx.crud_jdbc_template.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AuthorRepositoryTest extends UnitTestBase {

    @Autowired
    @Qualifier("authorRepositoryImpl")
    AuthorRepository authorRepository;

    @Autowired
    Fake fake;

    Author authorSave;

    @BeforeEach
    void saveInit() {
        Author authorInit = fake.getFakeAuthor();
        authorSave = authorRepository.save(authorInit);
    }

    @Test
    void save() {
        Author author = fake.getFakeAuthor();
        Author save = authorRepository.save(author);
        assertNotNull(save);
    }

    @Test
    void saveParam() {
        Author author = fake.getFakeAuthor();
        Author save = authorRepository.saveParam(author);
        assertNotNull(save);
    }

    @Test
    void saveId() {
        Author author = fake.getFakeAuthor();
        Long aLong = authorRepository.saveId(author);
        assertNotNull(aLong);
    }

    @Test
    void findById() {
        Optional<Author> author = authorRepository.findById(authorSave.getId());
        assertEquals(authorSave.getId(), author.get().getId());
    }

    @Test
    void update() {
        authorSave.setName("АРКАДИЙ");
        authorRepository.update(authorSave);
        Optional<Author> author = authorRepository.findById(authorSave.getId());
        assertEquals("АРКАДИЙ", author.get().getName());
    }

    @Test
    void listAuthor() {
        List<Author> authorList = authorRepository.listAuthor();
        assertFalse(authorList.isEmpty());
    }

    @Test
    void listAuthorWithBooks() {
        List<Author> authors = authorRepository.listAuthorWithBooks();
        System.out.println(authors);
    }

    @Test
    void delete() {
        boolean delete = authorRepository.delete(authorSave.getId());
        assertTrue(delete);
    }

}
