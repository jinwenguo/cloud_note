package cn.tedu.cloud_note.controller.user;

import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.service.UserService;
import cn.tedu.cloud_note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserRegistController {
    @Resource
    private UserService userService;
  /*  @RequestMapping("/regist.do")
    @ResponseBody//json输出
    public NoteResult<Object> execute(String name, String password, String nick){
        //调用Userserivce处理登录请求
        System.out.println(name+","+password+","+nick);
        NoteResult<Object> result = userService.addUser(name,password,nick);
        return result;

    }*/


    @RequestMapping("/regist.do")
    @ResponseBody
    public NoteResult<User> execute1(@RequestBody User user){
        System.out.println(user);
        NoteResult<User> result = userService.abbUser1(user);
        return result;
    }


}
