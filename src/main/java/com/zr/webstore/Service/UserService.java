package com.zr.webstore.Service;

import com.zr.webstore.mapper.UserMapper;
import com.zr.webstore.model.User;
import com.zr.webstore.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public User testLogin(String name) {
        User user = queryUserByName(name);
        return user;
    }

    public User regist(String name, String pwd, String phone,String email) {
        User user = new User();
        user.setName(name);
        user.setPwd(pwd);
        user.setPhone(phone);
        user.setType(2);
        user.setEmail(email);
        userMapper.insert(user);
        return user;
    }

    /**
     * 判断用户名是否存在
     * @param name
     * @return true 用户名存在 false 不存在
     */
        public boolean existsName(String name) {
        User user = queryUserByName(name);
        if(user!=null){
            return true;
        }else {
            return false;
        }
    }
//    根据用户名查询用户信息
public User queryUserByName(String name) {
    UserExample userExample = new UserExample();
    userExample.createCriteria()
            .andNameEqualTo(name);
    List<User> users = userMapper.selectByExample(userExample);
    User user = null;
    if (users.size() != 0 && users != null) {
        user = users.get(0);
    }
    return user;
}
}
