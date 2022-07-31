package com.github.kosurov.util;

import com.github.kosurov.dao.PersonDAO;
import com.github.kosurov.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personDAO.getPersonByName(person.getName()).isPresent()) {
            errors.rejectValue("name", "", "Person is already exists");
        }
    }
}
