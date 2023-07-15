package cn.tedu.cloud_note.service;

import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.util.NoteResult;

import java.util.List;

public interface NoteService {
    NoteResult<List<Note>> loadBookNotes(String bookId);
    NoteResult loadNote(String noteId);
    NoteResult updateNote(String noteId,String title,String body);
    NoteResult<String> addNote(String userId,String bookId,String title);
    NoteResult deleteNote(String noteId);
}
