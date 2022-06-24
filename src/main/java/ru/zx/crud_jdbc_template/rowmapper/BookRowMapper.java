package ru.zx.crud_jdbc_template.rowmapper;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import ru.zx.crud_jdbc_template.entity.Author;
import ru.zx.crud_jdbc_template.entity.Book;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

public class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Author author = new Author();
        author.setId(rs.getLong("a_id"));
        author.setSurname(rs.getString("surname"));
        author.setName(rs.getString("a_name"));
        author.setPatronymic(rs.getString("patronymic"));
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setName(rs.getString("b_name"));
        Date date_publication = rs.getDate("date_publication");
        if(date_publication!=null) {
            book.setDatePublication(date_publication.toLocalDate());
        }
        book.setAuthor(author);
        return book;
    }
}
