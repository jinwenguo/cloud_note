package cn.tedu.cloud_note.service;

import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.util.NoteResult;

import java.util.List;

public interface BookService {
    NoteResult<List<Book>> loadUserBooks(String userId);

    NoteResult<Book> addBook(String userId, String bookName);

    NoteResult deleteBook(String bookId);
}
