package ru.zx.crud_jdbc_template.repository.impl;

import org.flywaydb.core.internal.jdbc.RowMapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.zx.crud_jdbc_template.entity.Book;
import ru.zx.crud_jdbc_template.repository.BookRepository;
import ru.zx.crud_jdbc_template.rowmapper.BookRowMapper;

import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {

    JdbcTemplate jdbcTemplate;

    public BookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_FIND_BY_ALL = "select b.id id, b.name b_name, date_publication, " +
            " a.id a_id, surname, a.name a_name, patronymic from books b, authors a where b.author_id=a.id";
    private static final String SQL_FIND_BY_ID_ALL = "select * from books where id=?";

    @Override
    public Book save(Book book) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("books").usingGeneratedKeyColumns("id");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", book.getName());
        parameterSource.addValue("date_publication", book.getDatePublication());
        parameterSource.addValue("author_id",book.getAuthor().getId());
        Number key = simpleJdbcInsert.executeAndReturnKey(parameterSource);
        book.setId(key.longValue());
        return book;
    }

    @Override
    public List<Book> bookList() {
        List<Book> bookList = jdbcTemplate.query(SQL_FIND_BY_ALL, new BookRowMapper());
        return bookList;
    }

    @Override
    public List<Book> bookListInId(Long id) {
        List<Book> query = jdbcTemplate.query(SQL_FIND_BY_ID_ALL, BeanPropertyRowMapper.newInstance(Book.class), id);
        return query;
    }
}
