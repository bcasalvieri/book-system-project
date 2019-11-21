package com.trilogyed.notequeueconsumer;

import com.trilogyed.notequeueconsumer.service.NoteQueueConsumerServiceLayer;
import com.trilogyed.notequeueconsumer.util.messages.Note;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    private NoteQueueConsumerServiceLayer service;

    MessageListener(NoteQueueConsumerServiceLayer service) {
        this.service = service;
    }

    @RabbitListener(queues = NoteQueueConsumerApplication.QUEUE_NAME)
    public void receiveNote(Note note) {
        if (note.getId() == 0) service.createNote(note);
        else service.updateNote(note);
    }
}
