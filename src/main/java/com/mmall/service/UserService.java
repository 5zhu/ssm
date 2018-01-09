package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;

/**
 * Created by gegf
 */
public interface UserService {

    /**
     * 用户登录接口
     * @param username
     * @param password
     * @return
     */
    public ServerResponse<User> login(String username, String password);

    /**
     * 注册
     * @param user
     * @return
     */
    public ServerResponse<String> register(User user);
}
