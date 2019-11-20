package com.company.bookservice.service;

import com.company.bookservice.dao.BookRepository;
import com.company.bookservice.model.Book;
import com.company.bookservice.model.BookViewModel;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class BookServiceLayerTest {

    private BookRepository bookRepo;
    private BookServiceLayer service;

    @Before
    public void setUp() throws Exception {
        setUpBookRepoMock();
        service = new BookServiceLayer(bookRepo);
    }

    @Test
    public void shouldCreateAndGetBook() {
        BookViewModel book = new BookViewModel();
        book.setTitle("Harry Potter and the Sorcerer's Stone");
        book.setAuthor("J.K. Rowling");

        book = service.createBook(book);
        BookViewModel fromService = service.getBook(book.getId());
        assertEquals(book, fromService);
    }

    @Test
    public void shouldGetAllBooks() {
        BookViewModel book = new BookViewModel();
        book.setTitle("Harry Potter and the Sorcerer's Stone");
        book.setAuthor("J.K. Rowling");
        book = service.createBook(book);

        List<BookViewModel> books = new ArrayList<>();
        books.add(book);

        List<BookViewModel> fromService = service.getAllBooks();
        assertEquals(books, fromService);
    }

    private void setUpBookRepoMock() {
        bookRepo = mock(BookRepository.class);

        Book book = new Book();
        book.setId(1);
        book.setTitle("Harry Potter and the Sorcerer's Stone");
        book.setAuthor("J.K. Rowling");

        Book book1 = new Book();
        book1.setTitle("Harry Potter and the Sorcerer's Stone");
        book1.setAuthor("J.K. Rowling");

        List<Book> books = new ArrayList<>();
        books.add(book);

        doReturn(book).when(bookRepo).save(book1);
        doReturn(book).when(bookRepo).findById(1);
        doReturn(books).when(bookRepo).findAll();
    }
}