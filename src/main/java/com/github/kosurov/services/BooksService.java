package com.github.kosurov.services;

import com.github.kosurov.models.Book;
import com.github.kosurov.models.Person;
import com.github.kosurov.repositories.BooksRepository;
import com.github.kosurov.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Page<Book> findAll(Pageable pageable) {
        return booksRepository.findAll(pageable);
    }

    public Book findById(int id) {
        Optional<Book> book = booksRepository.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void setBookOwner(int bookId, int personId) {
        Optional<Book> book = booksRepository.findById(bookId);
        Optional<Person> owner = peopleRepository.findById(personId);
        if (book.isPresent() && owner.isPresent()) {
            book.get().setOwner(owner.get());
        }
    }

    public Optional<Person> getBookOwner(int bookId) {
        Optional<Book> book = booksRepository.findById(bookId);
        Person owner = null;
        if (book.isPresent()) {
            owner = book.get().getOwner();
        }
        return Optional.ofNullable(owner);
    }

    @Transactional
    public void returnBook(int id) {
        Optional<Book> book = booksRepository.findById(id);
        if (book.isPresent()) {
             book.get().setOwner(null);
        }
    }
}
