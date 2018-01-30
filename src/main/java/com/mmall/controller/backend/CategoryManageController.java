package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 分类管理Controller
 */
@Controller
@RequestMapping("/manager/category")
public class CategoryManageController {

    @Autowired
    private UserService userService;

    public ServerResponse addCategory(HttpSession session, String categoryName,
          @RequestParam(value = "parentId", defaultValue = "0") int parentId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }
        if(userService.checkAdminRole(user).isSuccess()){

        }else{
            return ServerResponse.createByErrorMessage("无权限操作，需要有管理员权限");
        }
        return null;

    }
}
