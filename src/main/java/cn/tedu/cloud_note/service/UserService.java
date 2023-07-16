package cn.tedu.cloud_note.service;

import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.util.NoteResult;

public interface UserService {
    NoteResult<User> checkLogin(String name, String password);
    NoteResult<User> fid(String name, String password);
    NoteResult<User> heckLogin1( String name, String password);
    NoteResult<Object> addUser(String name,String nick,String password);
    NoteResult<User> abbUser1(User user);

}
