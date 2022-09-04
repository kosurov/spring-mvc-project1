package com.github.kosurov.repositories;

import com.github.kosurov.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findBooksByTitleStartingWith(String title);
}
