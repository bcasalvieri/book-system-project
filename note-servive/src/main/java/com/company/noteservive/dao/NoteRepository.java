package com.company.noteservive.dao;

import com.company.noteservive.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

    public List<Note> findByBookId(int bookId);

}
