package ru.zx.crud_jdbc_template.faker;

import org.springframework.stereotype.Component;
import ru.zx.crud_jdbc_template.entity.Author;
import ru.zx.crud_jdbc_template.entity.Book;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class Fake {


    public Author getFakeAuthor() {
        return Author.builder()
                .surname("СТРУГАЦКИХ")
                .name("БОРИС")
                .patronymic("НАТАНОВИЧ")
                .build();
    }

    public Book getFakeBook() {
        return Book.builder()
                .name("МАЛЫШ")
                .datePublication(LocalDate.of(1971, 1, 1))
                .build();
    }

}
