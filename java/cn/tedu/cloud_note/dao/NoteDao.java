package cn.tedu.cloud_note.dao;

import cn.tedu.cloud_note.entity.Note;

import java.util.List;

public interface NoteDao {
    List<Note> findByBookId(String bookId);
    Note findById(String noteId);
    int updateNote(Note note);
    void saveNote(Note note);
    int deleteNote(String noteId);
}
