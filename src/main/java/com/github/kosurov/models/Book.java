package com.github.kosurov.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {

    private int id;

    private int personId;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 100, message = "Author name should be between 2 and 100 characters")
    private String title;

    @NotEmpty(message = "Author name should not be empty")
    @Size(min = 2, max = 100, message = "Author name should be between 2 and 100 characters")
    private String author;

    @Min(value = 0, message = "The year of writing should be valid")
    private int yearOfWriting;

    public Book() {

    }

    public Book(int id, int personId, String title, String author, int yearOfWriting) {
        this.id = id;
        this.personId = personId;
        this.title = title;
        this.author = author;
        this.yearOfWriting = yearOfWriting;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfWriting() {
        return yearOfWriting;
    }

    public void setYearOfWriting(int yearOfWriting) {
        this.yearOfWriting = yearOfWriting;
    }
}
