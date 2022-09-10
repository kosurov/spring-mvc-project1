package com.github.kosurov.services;

import com.github.kosurov.models.Book;
import com.github.kosurov.models.Person;
import com.github.kosurov.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findById(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        return person.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public List<Book> getBooksByPersonId(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());
            for (Book book : person.get().getBooks()) {
                if(book.getLendingTime() != null) {
                    if(System.currentTimeMillis() - book.getLendingTime().getTime() >
                    10 * 24 * 60 * 60 * 1000) {
                        book.setExpired(true);
                    }
                }
            }
            return person.get().getBooks();
        }
        return Collections.emptyList();
    }

    public Optional<Person> getPersonByName(String name) {
        return peopleRepository.findByName(name);
    }
}
