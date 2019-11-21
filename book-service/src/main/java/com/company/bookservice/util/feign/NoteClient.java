package com.company.bookservice.util.feign;

import com.company.bookservice.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "note-service")
public interface NoteClient {
    @GetMapping(value = "/notes/book/{book_id}")
    public List<Note> getNotesByBookId(@PathVariable int book_id);

    @DeleteMapping("/notes/{id}")
    public void deleteNote(@PathVariable int id);
}
