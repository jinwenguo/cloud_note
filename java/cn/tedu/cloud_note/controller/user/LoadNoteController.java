package cn.tedu.cloud_note.controller.user;

import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/note")
public class LoadNoteController {
    @Resource
    private NoteService noteService;

    @RequestMapping("/loadnotes.do")
    @ResponseBody
    public NoteResult execute(String bookId){
        NoteResult result = noteService.loadBookNotes(bookId);
        return result;

    }

    @RequestMapping("/load.do")
    @ResponseBody
    public NoteResult execute1(String noteId){
        NoteResult result = noteService.loadNote(noteId);
        return result;
    }

    @RequestMapping("/updata.do")
    @ResponseBody
    public NoteResult execute2(String noteId, String title, String body){
        NoteResult result = noteService.updateNote(noteId,title,body);
        return result;
    }

    @RequestMapping("/add.do")
    @ResponseBody
    public NoteResult<String> addNote(String userId,String bookId,String title){
        NoteResult<String> result = noteService.addNote(userId, bookId, title);
        return result;

    }

    @RequestMapping("/recycle.do")
    @ResponseBody
    public NoteResult deleteNote(String noteId){
        return noteService.deleteNote(noteId);
    }
}
