package cn.tedu.cloud_note.controller.user;

import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.service.ShareService;
import cn.tedu.cloud_note.util.NoteResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/share")
public class ShareController {
    @Resource
    private ShareService shareService;

    @RequestMapping("/add.do")
    public NoteResult exce(String noteId) {
        return shareService.addShare(noteId);
    }

    @RequestMapping("/search.do")
    public NoteResult<List<Share>> exce1(String shareTitle, int page) {
        return shareService.findSearch(shareTitle, page);
    }
}
