package com.trilogyed.notequeueconsumer.util.feign;

import com.trilogyed.notequeueconsumer.util.messages.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "note-service")
public interface NoteClient {
    @PostMapping(value = "/notes")
    public void createNote(@RequestBody Note note);

    @PutMapping(value = "/notes/{id}")
    public void updateNote(@PathVariable int id, @RequestBody Note note);
}
