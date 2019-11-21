package com.company.bookservice.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class BookViewModel {
    private int id;
    @NotEmpty(message = "Please enter a title.")
    @Size(max = 50, message = "Title cannot be more than 50 characters.")
    private String title;
    @NotEmpty(message = "Please enter an author.")
    @Size(max = 50, message = "Author cannot be more than 50 characters.")
    private String author;
    private List<Note> notesList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Note> getNotesList() {
        return notesList;
    }

    public void setNotesList(List<Note> notesList) {
        this.notesList = notesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookViewModel that = (BookViewModel) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(author, that.author) &&
                Objects.equals(notesList, that.notesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, notesList);
    }

    @Override
    public String toString() {
        return "BookViewModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", notesList=" + notesList +
                '}';
    }
}
