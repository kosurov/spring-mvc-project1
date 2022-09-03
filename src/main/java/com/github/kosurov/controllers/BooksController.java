package com.github.kosurov.controllers;

import com.github.kosurov.models.Book;
import com.github.kosurov.models.Person;
import com.github.kosurov.services.BooksService;
import com.github.kosurov.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model,
                        @RequestParam(name = "page", required = false) Integer page,
                        @RequestParam(name = "books_per_page", required = false) Integer booksPerPage,
                        @RequestParam(name = "sort_by_year", required = false) Boolean sortByYear) {

        if (page != null && booksPerPage != null) {
            if (sortByYear != null && sortByYear) {
                model.addAttribute("books", booksService.findAll(PageRequest.of(page, booksPerPage,
                                Sort.by("yearOfWriting"))).getContent());
            } else {
                model.addAttribute("books", booksService.findAll(PageRequest.of(page, booksPerPage))
                        .getContent());
            }
        } else if (sortByYear != null && sortByYear) {
            model.addAttribute("books", booksService.findAll(Sort.by("yearOfWriting")));
        } else {
            model.addAttribute("books", booksService.findAll());
        }
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        // Получим одну книгу по id и передадим на отображение в представление
        model.addAttribute("book", booksService.findById(id));
        Optional<Person> bookOwner = booksService.getBookOwner(id);

        if(bookOwner.isPresent()) {
            model.addAttribute("bookOwner", bookOwner.get());
        } else {
            model.addAttribute("people", peopleService.findAll());
        }
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/edit";
        booksService.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/set_person")
    public String setBookOwner(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        booksService.setBookOwner(id, person.getId());
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/return_book")
    public String returnBook(@PathVariable("id") int id) {
        booksService.returnBook(id);
        return "redirect:/books/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }
}
