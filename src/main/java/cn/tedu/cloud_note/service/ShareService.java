package cn.tedu.cloud_note.service;

import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.util.NoteResult;

import java.util.List;

public interface ShareService {
    NoteResult addShare(String noteId);

    NoteResult<List<Share>> findSearch(String shareTitle, int page);
}
