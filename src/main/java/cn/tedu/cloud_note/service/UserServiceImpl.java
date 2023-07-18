package cn.tedu.cloud_note.service;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("userService")//扫描的Spring容器
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public NoteResult<User> checkLogin(String name, String password) {
        //接受结果数据
        NoteResult<User> result = new NoteResult<User>();
        //按参考name查询数据库
        User user = userDao.findByName(name);
        //用户名检查
        if (user == null) {
            result.setStatus(1);
            result.setMsg("用户名不存在");
            return result;
        }
        //密码检查
        String md5Password = NoteUtil.md5(password);
        if (!user.getCn_user_password().equals(md5Password)) {
            result.setStatus(2);
            result.setMsg("密码错误");
            return result;
        }
        //用户名和密码正确
        result.setStatus(0);
        result.setMsg("登录成功");
        result.setData(user);
        return result;
    }


    @Override
    public NoteResult<User> fid(String name, String password) {
        NoteResult<User> result = new NoteResult<>();

        //todo  根据给定用户名查找User对象
        User user = userDao.findByName(name);
        if (user == null) {
            result.setMsg("登录失败");
            result.setStatus(1);
            return result;
        }
        // todo 加密给定密文 password
        String s = NoteUtil.md5(password);

        // todo 判定给定的密码与数据库查询到的密码是否匹配
        if (s.equals(user.getCn_user_password())) {
            result.setData(user);
            return result;
        }
        result.setStatus(2);
        result.setMsg("密码错误");
        return result;



       /* //接受结果数据
        NoteResult<User> result = new NoteResult<User>();
        //按参考name查询数据库
        List<User> users = userDao.fin(name);

        User user = null;
        for(int i = 0;i< users.size();i++){
            if(users.get(i).getCn_user_name().equals(name)){
                user = users.get(i);
                break;
            }
        }

        //用户名检查
      /             ;
        if(user==null){
            result.setStatus(1);
            result.setMsg("用户名不存在");
            return result;
        }
        //密码检查
        String md5Password = NoteUtil.md5(password);
        if(!user.getCn_user_password().equals(md5Password)){
            result.setStatus(2);
            result.setMsg("密码错误");
            return result;
        }
        //用户名和密码正确
        result.setStatus(0);
        result.setMsg("登录成功");
        result.setData(users.get(0));
        return result;*/
    }

    @Override
    public NoteResult<User> heckLogin1(String name, String password) {
        NoteResult<User> result = new NoteResult<>();
        String s = NoteUtil.md5(password);
        User users1 = userDao.findByName1(name, s);
        if (users1 == null) {
            result.setStatus(1);
            result.setMsg("密码或用户名不正确");
            return result;
        }
        result.setData(users1);
        return result;
    }

    @Override
    public NoteResult<Object> addUser(String name, String nick, String password) {
        //接受结果数据
        NoteResult<Object> result = new NoteResult<Object>();
        //用户检查
        User hasUser = userDao.findByName(name);
        //用户名检查
        if (hasUser != null) {
            result.setStatus(1);
            result.setMsg("用户已经被占用");
            return result;
        }
        //添加用户
        User user = new User();
        //设置用户名
        user.setCn_user_name(name);
        //设置密码
        String md5Password = NoteUtil.md5(password);
        user.setCn_user_password(md5Password);
        //设置nic
        user.setCn_user_nick(nick);
        //创建ID
        String id = NoteUtil.createId();
        //设置id
        user.setCn_user_id(id);
        //插入用户数据
        userDao.save(user);
        result.setStatus(0);
        result.setMsg("注册成功");
        return result;

    }

    @Override
    public NoteResult<User> abbUser1(User user) {
        //接受结果数据
        NoteResult<User> result = new NoteResult<>();
        //用户检查
        User hasUser = userDao.findByName(user.getCn_user_name());
        //用户名检查
        if (hasUser != null) {
            result.setStatus(1);
            result.setMsg("用户已经被占用");
            return result;
        }

        String md5Password = NoteUtil.md5(user.getCn_user_password());
        user.setCn_user_password(md5Password);
        user.setCn_user_id(NoteUtil.createId());
        //插入用户数据
        userDao.save(user);
        result.setStatus(0);
        result.setMsg("注册成功");
        return result;
    }
}
