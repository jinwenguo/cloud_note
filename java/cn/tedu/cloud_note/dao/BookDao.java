package cn.tedu.cloud_note.dao;

import cn.tedu.cloud_note.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookDao {
    List<Book> findByUserId(@Param("userId") String userId);
    void save(Book book);
    int deleteByNotebookId(@Param("notebookId") String notebookId);


}
