package com.github.kosurov.dao;

import com.github.kosurov.models.Book;
import com.github.kosurov.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("select * from person", new PersonMapper());
    }

    public Person show(int id) {
        return jdbcTemplate.query("select * from person where person_id = ?", new PersonMapper(), id)
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into person(name, year_of_birth) values(?, ?)",
                person.getName(), person.getYearOfBirth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("update person set name = ?, year_of_birth = ? where person_id = ?",
                updatedPerson.getName(), updatedPerson.getYearOfBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from person where person_id = ?", id);
    }

    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("select * from book where person_id = ?", new BookMapper(), id);
    }

    public Optional<Person> getPersonByName(String name) {
        return jdbcTemplate.query("select * from person where name = ?", new PersonMapper(), name)
                .stream().findAny();
    }
}
