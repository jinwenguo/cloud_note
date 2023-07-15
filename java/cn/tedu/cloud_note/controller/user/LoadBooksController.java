package cn.tedu.cloud_note.controller.user;

import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.service.BookService;
import cn.tedu.cloud_note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/book")
public class LoadBooksController {

    @Resource
    private BookService bookService;

    @RequestMapping("/loadbooks.do")
    @ResponseBody
    public NoteResult<List<Book>> execute(String userId) {
        NoteResult<List<Book>> result = bookService.loadUserBooks(userId);
        return result;

    }

    @RequestMapping("/add.do")
    @ResponseBody
    public NoteResult<Book> execute1(String userId, String bookName) {
        System.out.println();
        return bookService.addBook(userId, bookName);
    }

    @RequestMapping("/delete.do")
    @ResponseBody
    public NoteResult deleteBooks(String bookId) {
        System.out.println();
        NoteResult result = bookService.deleteBook(bookId);
        return result;
    }

}
