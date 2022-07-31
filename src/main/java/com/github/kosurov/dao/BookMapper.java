package com.github.kosurov.dao;

import com.github.kosurov.models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("book_id"));
        book.setPersonId(resultSet.getInt("person_id"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        book.setYearOfWriting(resultSet.getInt("year_of_writing"));
        return book;
    }
}
