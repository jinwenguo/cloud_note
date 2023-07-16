package cn.tedu.cloud_note.service;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("noteService")
public class NoteServiceimpl implements NoteService{
    @Resource
    private NoteDao noteDao;
    @Override
    public NoteResult loadBookNotes(String bookId) {
        //返回数据集合
        List<Note> list = noteDao.findByBookId(bookId);
        List<Note> helloNote = new ArrayList<>();
        for(int i = 0; i < list.size();i++){
            Note note = list.get(i);
            if( "你好".equals(note.getCn_note_title())){
                helloNote.add(note);
            }
        }

        List<Note> collect = list.stream().filter(c -> "你好".equals(c.getCn_note_title()))
                .collect(Collectors.toList());


        //构建Result
        NoteResult result = new NoteResult<>();
        result.setStatus(0);
        result.setMsg("加载笔记成功！");
        result.setData(list);
        return result;
    }

    @Override
    public NoteResult loadNote(String noteId) {
        NoteResult result = new NoteResult();
        Note note = noteDao.findById(noteId);
        result.setStatus(0);
        result.setMsg("加载笔记成功");
        result.setData(note);
        return result;
    }

    @Override
    public NoteResult updateNote(String noteId, String title, String body) {
        NoteResult result = new NoteResult();
        Note note = new Note();
        Note note1 = new Note(noteId,title,body,System.currentTimeMillis());
        note.setCn_note_id(noteId);
        note.setCn_note_title(title);
        note.setCn_note_body(body);
        note.setCn_note_last_modify_time(System.currentTimeMillis());
        noteDao.updateNote(note);
        result.setStatus(0);
        result.setMsg("加载成功");
        return result;
    }

    @Override
    public NoteResult<String> addNote(String userId, String bookId, String title) {
        NoteResult<String> result = new NoteResult<>();
        Note note = new Note();
        note.setCn_user_id(userId);
        note.setCn_notebook_id(bookId);
        note.setCn_note_title(title);
        note.setCn_note_status_id("1");
        note.setCn_note_type_id("1");
        note.setCn_note_body("");
        note.setCn_note_create_time(System.currentTimeMillis());
        note.setCn_note_last_modify_time(System.currentTimeMillis());
        String noteId = NoteUtil.createId();
        note.setCn_note_id(noteId);
        noteDao.saveNote(note);
        result.setStatus(0);
        result.setMsg("添加笔记成功");
        return result;
    }

    @Override
    public NoteResult deleteNote(String noteId) {
        NoteResult result = new NoteResult();
        noteDao.deleteNote(noteId);
        result.setStatus(0);
        result.setMsg("删除笔记");
        result.setData(noteId);
        return result;
    }
}
