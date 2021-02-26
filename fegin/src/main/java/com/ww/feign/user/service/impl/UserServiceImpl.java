package com.ww.feign.user.service.impl;

import com.ww.feign.user.pojo.User;
import com.ww.feign.user.dao.UserDAO;
import com.ww.feign.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User getUserInfo() {
        return userDAO.getUserInfo();
    }
}
