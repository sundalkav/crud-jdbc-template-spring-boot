package ru.zx.crud_jdbc_template.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Book {
    private Long id;
    private String name;
    private LocalDate datePublication;
    private Author author;

}
