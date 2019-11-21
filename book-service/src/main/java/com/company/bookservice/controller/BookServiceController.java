package com.company.bookservice.controller;

import com.company.bookservice.model.BookViewModel;
import com.company.bookservice.service.BookServiceLayer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookServiceController {

    @Autowired
    private BookServiceLayer service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookViewModel postBook(@RequestBody @Valid BookViewModel book) {
        return service.createBook(book);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookViewModel getBook(@PathVariable int id) {
        BookViewModel book = service.getBook(id);
        if (book == null) throw new IllegalArgumentException("No book found with id " + id);
        return book;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookViewModel> getAllBooks() {
        List<BookViewModel> books = service.getAllBooks();
        if (books != null && books.size() == 0) throw new IllegalArgumentException("Uh oh...No books found!");
        return books;
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@PathVariable int id, @RequestBody @Valid BookViewModel book) {
        if (book.getId() == 0) book.setId(id);
        if (id != book.getId()) throw new IllegalArgumentException("Book ID on path must match ID in the book object.");
        service.updateBook(book);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int id) {
        service.deleteBook(id);
    }
}
