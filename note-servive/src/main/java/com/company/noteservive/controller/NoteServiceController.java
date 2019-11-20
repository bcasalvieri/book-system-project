package com.company.noteservive.controller;

import com.company.noteservive.dao.NoteRepository;
import com.company.noteservive.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
public class NoteServiceController {

    @Autowired
    private NoteRepository repo;



    @PostMapping("/notes")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createNote(@RequestBody @Valid Note note) {
        repo.save(note);
    }

    @PostMapping("/notes")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Note> getAllNotes() {
        return repo.findAll();
    }

    @GetMapping("/notes/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Note getNoteById(@PathVariable int id) {
        return repo.findById(id).orElse(null);
    }

    @GetMapping("/notes/book/{book_id}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Note> getNotesByBookId(@PathVariable("book_id") int id) {
        return repo.findByBookId(id);
    }

    @PutMapping("/notes/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateNote(@RequestBody @Valid Note note) {
        repo.save(note);
    }

    @DeleteMapping("/notes/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteNoteById(@PathVariable int id) {
        repo.deleteById(id);
    }
}
