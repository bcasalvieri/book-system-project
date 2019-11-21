package com.trilogyed.notequeueconsumer.service;

import com.trilogyed.notequeueconsumer.util.feign.NoteClient;
import com.trilogyed.notequeueconsumer.util.messages.Note;
import org.springframework.stereotype.Component;

@Component
public class NoteQueueConsumerServiceLayer {

    private NoteClient client;

    NoteQueueConsumerServiceLayer(NoteClient client) {
        this.client = client;
    }

    public void createNote(Note note) {
        client.createNote(note);
    }

    public void updateNote(Note note) {
        client.updateNote(note.getId(), note);
    }

    public void deleteNote(Note note) {
        client.deleteNote(note.getId());
    }
}
