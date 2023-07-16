package cn.tedu.cloud_note.dao;

import cn.tedu.cloud_note.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    User findByName(String name);
    void save(User user);

    List<User> fin(String name);
    User findByName1(@Param("name") String name, @Param("password") String password);


}
