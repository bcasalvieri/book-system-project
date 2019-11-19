//package com.company.bookservice.service;
//
//import com.company.bookservice.dao.BookRepository;
//import com.company.bookservice.model.Book;
//import com.company.bookservice.model.BookViewModel;
//
//import java.util.List;
//
//public class BookServiceLayer {
//    private BookRepository bookRepo;
//
//    public BookViewModel createBook(BookViewModel bookViewModel) {
//        Book book = new Book();
//        book.setTitle(bookViewModel.getTitle());
//        book.setAuthor(bookViewModel.getAuthor());
//        book = bookRepo.save(book);
//
//        // call note client and get all notes
//        bookViewModel.setId(book.getId());
//        bookViewModel.setNotesList();
//
//        return bookViewModel;
//    }
//
//    public BookViewModel getBook(int id) {
//        Book book = bookRepo.findById(id).orElse(null);
//
//        // call client to get notes list for book
//        List<String> notes = client.getNotesByBookId();
//        return buildBookViewModel(book, notes);
//    }
//
//    private BookViewModel buildBookViewModel(Book book, List<String> notes) {
//        BookViewModel bvm = new BookViewModel();
//        bvm.setId(book.getId());
//        bvm.setTitle(book.getTitle());
//        bvm.setAuthor(book.getAuthor());
//        bvm.setNotesList(notes);
//        return bvm;
//    }
//}
