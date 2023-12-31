package cn.tedu.cloud_note.service;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.dao.ShareDao;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("shareService")
public class ShareServiceImpl implements ShareService {
    @Resource
    private ShareDao shareDao;
    @Resource
    private NoteDao noteDao;

    @Override
    public NoteResult addShare(String noteId) {
        Note note = noteDao.findById(noteId);

        Share share = new Share();
        share.setShareTitle(note.getCn_note_title());
        share.setShareBody(note.getCn_note_body());
        share.setShareId(noteId);
        share.setShareId(NoteUtil.createId());
        shareDao.insertAll(share);
        NoteResult result = new NoteResult();
        result.setStatus(0);
        result.setMsg("分享成功");
        return result;
    }
}
