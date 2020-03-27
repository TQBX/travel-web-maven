package com.travel.service.impl;

import com.travel.dao.UserDao;
import com.travel.dao.impl.UserDaoImpl;
import com.travel.domain.User;
import com.travel.service.UserService;
import com.travel.utils.MailUtils;
import com.travel.utils.UuidUtils;

import java.util.List;

/**
 * @author Summerday
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    @Override
    public List<User> UserFindAll() {
        return userDao.findAll();
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean register(User user) {
        //根据用户名查询用户对象
        User u = userDao.findUserByUsername(user.getUsername());

        if(u!=null){
            return false;
        }
        //保存用户信息
        //设置激活码
        user.setCode(UuidUtils.getUuid());
        //设置状态
        user.setStatus("N");

        userDao.UserSave(user);
        //发送邮件
        String content = "<a href='http://localhost/webmvn/user/active?code="+user.getCode()+"'>点击激活[黑马旅游网]</a>";

        MailUtils.sendMail(user.getEmail(),content,"激活邮件");


        return true;
    }

    /**
     * 激活用户
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {

        //根据激活码查询用户对象
        User user = userDao.findUserByCode(code);
        //调用dao的修改激活状态的方法
        if(user!=null){
            userDao.updateStatus(user);
            return true;
        }else {
            return false;
        }

    }

    /**
     * 登录方法
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
