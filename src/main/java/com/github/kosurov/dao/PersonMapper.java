package com.github.kosurov.dao;

import com.github.kosurov.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getInt("person_id"));
        person.setName(resultSet.getString("name"));
        person.setYearOfBirth(resultSet.getInt("year_of_birth"));
        return person;
    }
}
