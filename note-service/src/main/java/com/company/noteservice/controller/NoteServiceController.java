package com.company.noteservice.controller;

import com.company.noteservice.dao.NoteRepository;
import com.company.noteservice.model.Note;
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

    @GetMapping("/notes")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Note> getAllNotes() {
        return repo.findAll();
    }

    @GetMapping("/notes/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Note getNoteById(@PathVariable int id) {
        return repo.findById(id).orElse(null);
    }

    @GetMapping("/notes/book/{bookId}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Note> getNotesByBookId(@PathVariable int bookId) {
        return repo.findByBookId(bookId);
    }

    @PutMapping("/notes/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateNote(@PathVariable int id, @RequestBody @Valid Note note) {
        repo.save(note);
    }

    @DeleteMapping("/notes/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteNoteById(@PathVariable int id) {
        repo.deleteById(id);
    }
}
