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

    /**
     *  忘记密码-重置
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken);

    /**
     * 登录用户重置密码
     * @param passwordOld
     * @param passwordNew
     * @param user
     * @return
     */
    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    ServerResponse<User> updateUserInformation(User user);

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    ServerResponse<User> getInformation(Integer id);

    ServerResponse checkAdminRole(User user);
}
