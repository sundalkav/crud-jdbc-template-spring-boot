package ru.zx.crud_jdbc_template.rowmapper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.zx.crud_jdbc_template.entity.Author;
import ru.zx.crud_jdbc_template.entity.Book;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AuthorExtractor implements ResultSetExtractor<List<Author>> {
    @Override
    public List<Author> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, Author> mapData = new HashMap<>();
        Author author = null;
        while (rs.next()) {
            Long author_id = rs.getLong("a_id");
            author = mapData.get(author_id);
            if (author == null) {
                author = new Author();
                author.setId(rs.getLong("a_id"));
                author.setSurname(rs.getString("surname"));
                author.setName(rs.getString("a_name"));
                author.setPatronymic(rs.getString("patronymic"));
                author.setBookList(new ArrayList<>());
                mapData.put(author_id, author);
            }
            Long b_id = rs.getLong("b_id");
            if (b_id > 0) {
                Book book = new Book();
                book.setId(b_id);
                book.setName(rs.getString("b_name"));
                Date date_publication = rs.getDate("date_publication");
                if (date_publication != null) {
                    book.setDatePublication(date_publication.toLocalDate());
                }
                author.getBookList().add(book);
            }
        }
        return new ArrayList<Author>(mapData.values());
    }
}
