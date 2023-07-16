package cn.tedu.cloud_note.service;

import cn.tedu.cloud_note.dao.BookDao;
import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service("bookService")
public class BookServiceImpl implements BookService {
    @Resource
    private BookDao bookDao;

    @Override
    public NoteResult<List<Book>> loadUserBooks(String userId) {
        NoteResult<List<Book>> result = new NoteResult();
        List<Book> list = bookDao.findByUserId(userId);
        result.setStatus(0);
        result.setMsg("查询笔记成功");
        result.setData(list);
        return result;
    }

    @Override
    public NoteResult<Book> addBook(String userId, String bookName) {
        NoteResult<Book> result = new NoteResult<>();

        List<Book> books = bookDao.findByUserId(userId);

        List<Book> collect = books.stream().filter(book -> bookName.equals(book.getCn_notebook_name()))
                .collect(Collectors.toList());
        if (!collect.isEmpty()) {
            result.setStatus(1);
            result.setMsg("笔记本名称已经存在");
            return result;
        }

/*

        for (Book value : books) {
            String cnNotebookName = value.getCn_notebook_name();
            if (cnNotebookName.equals(bookName)) {
                result.setStatus(1);
                result.setMsg("笔记本名称已经存在");
                return result;
            }
        }
*/


        Book book = new Book();
        String bookId = NoteUtil.createId();
        book.setCn_notebook_id(bookId);
        book.setCn_user_id(userId);
        book.setCn_notebook_type_id("5");
        book.setCn_notebook_name(bookName);
        book.setCn_notebook_desc("");
        book.setCn_notebook_createtime(new Timestamp(System.currentTimeMillis()));
        bookDao.save(book);
        result.setStatus(0);
        result.setMsg("创建笔记本成功");
        result.setData(book);
        return result;
    }

    @Override
    public NoteResult deleteBook(String bookId) {
        NoteResult result = new NoteResult();
        bookDao.deleteByNotebookId(bookId);
        result.setStatus(0);
        result.setMsg("删除笔记本成功");
        return result;
    }
}
