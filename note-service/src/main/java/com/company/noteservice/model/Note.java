package com.company.noteservice.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id", nullable = false)
    private int id;
    @Column(name = "book_id", nullable = false)
    private int bookId;
    @Column(length = 255, nullable = false)
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note1 = (Note) o;
        return getId() == note1.getId() &&
                getBookId() == note1.getBookId() &&
                Objects.equals(getNote(), note1.getNote());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBookId(), getNote());
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", note='" + note + '\'' +
                '}';
    }
}
