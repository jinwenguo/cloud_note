package test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;

public class TestUserDao {
    @Test
    public void testCase() {
        //实例化上下文对象
        ApplicationContext atx = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
        //获取dao对象
        UserDao dao = atx.getBean("userDao", UserDao.class);
        User user = dao.findByName("demo123");
        if(user!=null){
            System.out.println(user);
        }else{
            System.out.println("用户不存在");
        }
    }


    @Test
    public void test() {
        //实例化上下文对象
        ApplicationContext atx = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
        //获取dao对象
        UserDao dao = atx.getBean("userDao", UserDao.class);
        List<User> users = dao.fin("demo");
        if(!users.isEmpty()){
            System.out.println(users.get(0));
        }else{
            System.out.println("用户不存在");
        }
    }
}
