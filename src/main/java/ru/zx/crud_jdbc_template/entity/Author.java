package ru.zx.crud_jdbc_template.entity;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Author {
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private List<Book> bookList;
}
