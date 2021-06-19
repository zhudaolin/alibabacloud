package com.zdl.service;

import com.zdl.User;

public interface IuserService {
    User queryUser();
    User queryUserById(long id);
    User addUser(User user);
    User constructUser(long id, String name, Integer age);
}
