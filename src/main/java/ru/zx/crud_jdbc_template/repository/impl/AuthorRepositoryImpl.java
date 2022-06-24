package ru.zx.crud_jdbc_template.repository.impl;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.zx.crud_jdbc_template.entity.Author;
import ru.zx.crud_jdbc_template.entity.Book;
import ru.zx.crud_jdbc_template.repository.AuthorRepository;
import ru.zx.crud_jdbc_template.rowmapper.AuthorExtractor;

import java.sql.PreparedStatement;
import java.util.*;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    JdbcTemplate jdbcTemplate;

    public AuthorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Author> AUTHOR_ROW_MAPPER = BeanPropertyRowMapper.newInstance(Author.class);
    private static final RowMapper<Book> BOOK_ROW_MAPPER = BeanPropertyRowMapper.newInstance(Book.class);

    private static final String SQL_INSERT = "INSERT INTO authors (surname,name,patronymic) VALUES (?,?,?)";
    private static final String SQL_UPDATE = "UPDATE authors SET surname=?, name=?, patronymic=? WHERE id=?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM authors WHERE id=?";
    private static final String SQL_FIND_BY_ALL = "SELECT * FROM authors";
    private static final String SQL_FIND_ALL_WITH_BOOKS = "SELECT a.id a_id, a.surname, a.name a_name, a.patronymic,b.id b_id, b.name b_name, b.date_publication " +
            "FROM authors a LEFT JOIN books b on a.id=b.author_id";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM authors WHERE id=?";


    @Override
    public Author save(Author author) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("authors").usingGeneratedKeyColumns("id");
        final Number number = simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(author));
        author.setId(number.longValue());
        return author;
    }

    @Override
    public Author saveParam(Author author) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("authors").usingGeneratedKeyColumns("id");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("surname", author.getSurname());
        parameterSource.addValue("name", author.getName());
        parameterSource.addValue("patronymic", author.getPatronymic());
        Number number = simpleJdbcInsert.executeAndReturnKey(parameterSource);
        author.setId(number.longValue());
        return author;
    }

    @Override
    public Long saveId(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(SQL_INSERT, new String[] {"id"});
            preparedStatement.setString(1, author.getSurname());
            preparedStatement.setString(2, author.getName());
            preparedStatement.setString(3, author.getPatronymic());
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Author update(Author author) {
        int update = jdbcTemplate.update(SQL_UPDATE, new Object[]{author.getSurname(), author.getName(), author.getPatronymic(), author.getId()});
        return author;
    }


    @Override
    public Optional<Author> findById(Long id) {
        try {
            Author author = jdbcTemplate.queryForObject(SQL_FIND_BY_ID, AUTHOR_ROW_MAPPER, id);
            return Optional.ofNullable(author);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Author> listAuthor() {
        List<Author> authors = jdbcTemplate.query(SQL_FIND_BY_ALL, AUTHOR_ROW_MAPPER);
        return authors;
    }


    //OneToMany
    @Override
    public List<Author> listAuthorWithBooks() {
        List<Author> authors = jdbcTemplate.query(SQL_FIND_ALL_WITH_BOOKS, new AuthorExtractor());
        return authors;
    }

    @Override
    public boolean delete(Long id) {
        return jdbcTemplate.update(SQL_DELETE_BY_ID, id) > 0;
    }
}
