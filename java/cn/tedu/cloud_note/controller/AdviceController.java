package cn.tedu.cloud_note.controller;

import cn.tedu.cloud_note.util.NoteResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class AdviceController {
    @ExceptionHandler(value = Exception.class)
    public void handleException(Exception e) {
        e.printStackTrace();
        NoteResult result = new NoteResult();
        result.setStatus(1);
        result.setMsg(e.toString());
    }
}
