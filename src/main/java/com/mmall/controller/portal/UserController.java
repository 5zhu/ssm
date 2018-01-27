package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by gegf
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response = userService.login(username, password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    /**
     * 登出
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    public ServerResponse<String> register(User user){
        return userService.register(user);
    }

    /**
     * 注册校验接口
     * @param str
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkValid.do", method = RequestMethod.GET)
    public ServerResponse<String> checkValid(String str, String type){
        return userService.checkValid(str, type);
    }

    /**
     * 获取Session中的用户信息
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.GET)
    public ServerResponse<User> getUserInfo(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user != null){
            ServerResponse.createBySuccess(user);
        }
            return ServerResponse.createByErrorMessage("用户未登录，无法获取用户信息");
    }

    /**
     * 忘记密码，获取问题
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.GET)
    public ServerResponse<String> forgetGetQuestion(String username){
        return userService.selectQuestion(username);
    }

    /**
     * 检查问题答案
     * @param username
     * @param question
     * @param answer
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "forget_check_answer.do", method = RequestMethod.GET)
    public ServerResponse<String> checkAnswer(String username, String question, String answer){
        return userService.checkAnswer(username, question, answer);
    }

    @ResponseBody
    @RequestMapping(value = "forget_reset_password", method = RequestMethod.GET)
    public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken){
        return userService.forgetResetPassword(username, passwordNew, forgetToken);
    }

    public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }

        return userService.resetPassword(passwordOld, passwordNew, user);
    }


}
