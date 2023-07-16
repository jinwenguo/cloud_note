package cn.tedu.cloud_note.controller.user;

import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.service.UserService;
import cn.tedu.cloud_note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserLoginController {
    @Resource
    private UserService userService;
    /*@RequestMapping("/login.do")
    @ResponseBody//json输出
    public NoteResult<User> excute(String name, String password){
        //调用Userserivce处理登录请求
        System.out.println(name+","+password);
        NoteResult<User> result = userService.checkLogin(name, password);
        return result;

    }*/

    @RequestMapping("/login.do")
    @ResponseBody//json输出
    public NoteResult<User> excute1(String name, String password){
        //调用Userserivce处理登录请求
        System.out.println(name+","+password);
        NoteResult<User> result = userService.fid(name, password);
        return result;

    }

    /*@RequestMapping("/login.do")
    @ResponseBody//json输出
    public NoteResult<User> excute1(String name, String password){
        //调用Userserivce处理登录请求
        System.out.println(name+","+password);
        NoteResult<User> result = userService.heckLogin1(
                name, password);
        return result;

    }
*/
}
