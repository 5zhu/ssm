package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.CategoryService;
import com.mmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 分类管理Controller
 */
@Controller
@RequestMapping("/manager/category")
public class CategoryManageController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 添加category
     * @param session
     * @param categoryName
     * @param parentId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add_category.do", method = RequestMethod.POST)
    public ServerResponse addCategory(HttpSession session, String categoryName,
          @RequestParam(value = "parentId", defaultValue = "0") int parentId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }
        if(userService.checkAdminRole(user).isSuccess()){
            return categoryService.addCategory(categoryName, parentId);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作，需要有管理员权限");
        }
    }

    @ResponseBody
    @RequestMapping(value = "set_category.do", method = RequestMethod.POST)
    public ServerResponse setCategory(HttpSession session, Integer categoryId, String categoryName){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }
        if(userService.checkAdminRole(user).isSuccess()){
            return categoryService.updateCategory(categoryId, categoryName);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作，需要有管理员权限");
        }
    }


}
