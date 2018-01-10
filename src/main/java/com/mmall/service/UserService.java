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

    /**
     * 校验参数
     * @param str
     * @param type
     * @return
     */
    public ServerResponse<String> checkValid(String str, String type);

    /**
     * 获取问题
     * @param username
     * @return
     */
    public ServerResponse<String> selectQuestion(String username);

    /**
     * 验证问题答案
     * @param username
     * @param question
     * @param answer
     * @return
     */
    public ServerResponse<String> checkAnswer(String username, String question, String answer);
}
