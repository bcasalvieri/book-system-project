package com.company.bookservice.service;

import com.company.bookservice.dao.BookRepository;
import com.company.bookservice.model.Book;
import com.company.bookservice.model.BookViewModel;
import com.company.bookservice.model.Note;
import com.company.bookservice.util.feign.NoteClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookServiceLayer {

    public static final String EXCHANGE = "note-queue-exchange";
    public static final String ROUTING_KEY = "note.update.bookservice.service";

    private BookRepository bookRepo;
    private NoteClient client;
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public BookServiceLayer(BookRepository bookRepo, NoteClient client, RabbitTemplate rabbitTemplate) {
        this.bookRepo = bookRepo;
        this.client = client;
        this.rabbitTemplate = rabbitTemplate;
    }

    public BookViewModel createBook(BookViewModel bvm) {
        Book book = new Book();
        book.setTitle(bvm.getTitle());
        book.setAuthor(bvm.getAuthor());
        book = bookRepo.save(book);

        bvm.setId(book.getId());

        return bvm;
    }

    public BookViewModel getBook(int id) {
        Book book = bookRepo.findById(id).orElse(null);
        if (book == null) return null;
        else {
            BookViewModel bvm = buildBookViewModel(book);
            bvm.setNotesList(client.getNotesByBookId(id));
            return bvm;
        }
    }

    public List<BookViewModel> getAllBooks() {
        List<BookViewModel> books = bookRepo.findAll().stream()
                .map(this::buildBookViewModel)
                .collect(Collectors.toList());

        books.forEach(bookViewModel -> bookViewModel.setNotesList(client.getNotesByBookId(bookViewModel.getId())));

        return books;
    }

    public void updateBook(BookViewModel bookViewModel) {
        Book book = new Book();
        book.setId(bookViewModel.getId());
        book.setTitle(bookViewModel.getTitle());
        book.setAuthor(bookViewModel.getAuthor());
        bookRepo.save(book);
    }

    public void deleteBook(int id) {
        client.getNotesByBookId(id).forEach(note -> client.deleteNote(note.getId()));
        bookRepo.deleteById(id);
    }

    // ** USE RABBITMQ TO...
    // create Note
    public String createNote(Note note) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, note);

        return "Note Created";
    }
    // update Note

    private BookViewModel buildBookViewModel(Book book) {
        BookViewModel bvm = new BookViewModel();
        bvm.setId(book.getId());
        bvm.setTitle(book.getTitle());
        bvm.setAuthor(book.getAuthor());
        return bvm;
    }
}
