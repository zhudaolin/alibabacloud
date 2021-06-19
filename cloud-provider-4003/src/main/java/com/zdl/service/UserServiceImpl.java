package com.zdl.service;

import com.zdl.User;
import org.springframework.stereotype.Service;

/**
 * @author zhudaolin
 * @date 2021/6/7
 */
@Service
public class UserServiceImpl  implements IuserService{

    @Override
    public User queryUser() {
        return new User(1l,"zhudaolin",18);
    }

    @Override
    public User queryUserById(long id) {
        return new User(id,"zhudaolin",18);
    }

    @Override
    public User addUser(User user) {
        return user;
    }

    @Override
    public User constructUser(long id, String name, Integer age) {
        return new User(id,name,age);
    }
}
