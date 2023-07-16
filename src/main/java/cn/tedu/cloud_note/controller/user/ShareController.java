package cn.tedu.cloud_note.controller.user;

import cn.tedu.cloud_note.service.ShareService;
import cn.tedu.cloud_note.util.NoteResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/share")
public class ShareController {
    @Resource
    private ShareService shareService;

    @RequestMapping("/add.do")
    public NoteResult exce(String noteId) {
        return shareService.addShare(noteId);
    }
}
