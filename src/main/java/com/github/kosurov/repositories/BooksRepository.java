package com.github.kosurov.repositories;

import com.github.kosurov.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Book, Integer> {

}
