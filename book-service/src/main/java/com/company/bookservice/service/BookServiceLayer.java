package com.company.bookservice.service;

import com.company.bookservice.dao.BookRepository;
import com.company.bookservice.model.Book;
import com.company.bookservice.model.BookViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookServiceLayer {

    private BookRepository bookRepo;

    @Autowired
    public BookServiceLayer(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public BookViewModel createBook(BookViewModel bookViewModel) {
        Book book = new Book();
        book.setTitle(bookViewModel.getTitle());
        book.setAuthor(bookViewModel.getAuthor());
        book = bookRepo.save(book);

        // a notes from client

        return bookViewModel;
    }

    public BookViewModel getBook(int id) {
        Book book = bookRepo.findById(id).orElse(null);
        if (book == null) return null;
        else {
            BookViewModel bvm = buildBookViewModel(book);
            // get notes from client and set notes in BookViewModel
            return bvm;
        }
    }

    public List<BookViewModel> getAllBooks() {
        return null;
    }

    public void updateBook(BookViewModel bookViewModel) {
        Book book = new Book();
        book.setId(bookViewModel.getId());
        book.setTitle(bookViewModel.getTitle());
        book.setAuthor(bookViewModel.getAuthor());
        bookRepo.save(book);
    }

    public void deleteBook(int id) {
        bookRepo.deleteById(id);
    }

    private BookViewModel buildBookViewModel(Book book) {
        BookViewModel bvm = new BookViewModel();
        bvm.setId(book.getId());
        bvm.setTitle(book.getTitle());
        bvm.setAuthor(book.getAuthor());
        return bvm;
    }
}
