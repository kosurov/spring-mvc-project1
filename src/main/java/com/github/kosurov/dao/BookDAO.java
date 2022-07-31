package com.github.kosurov.dao;

import com.github.kosurov.models.Book;
import com.github.kosurov.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("select * from book", new BookMapper());
    }

    public Book show(int id) {
        return jdbcTemplate.query("select * from book where book_id = ?", new BookMapper(), id)
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("insert into book(title, author, year_of_writing) values(?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYearOfWriting());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("update book set title = ?, author = ?, year_of_writing = ? where book_id = ?",
                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYearOfWriting(), id);
    }

    public void setBookOwner(int bookId, int personId) {
        jdbcTemplate.update("update book set person_id = ? where book_id = ?", personId, bookId);
    }

    public Optional<Person> getBookOwner(int bookId) {
        return jdbcTemplate.query("select p.person_id, p.name, p.year_of_birth from book b join person p " +
                        "on b.person_id = p.person_id where b.book_id = ?", new PersonMapper(), bookId)
                .stream().findAny();
    }

    public void returnBook(int id) {
        jdbcTemplate.update("update book set person_id = null where book_id = ?", id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from book where book_id = ?", id);
    }
}
