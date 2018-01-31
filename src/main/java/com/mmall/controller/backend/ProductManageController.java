package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.pojo.User;
import com.mmall.service.ProductService;
import com.mmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 *Create by gegf
 */

@Controller
@RequestMapping("/manage/product")
public class ProductManageController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    public ServerResponse<Product> manageProductDetail(Integer productId){
        if(productId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                   ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return null;
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ServerResponse productSave(HttpSession session, Product product){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),
                    ResponseCode.NEED_LOGIN.getDesc());
        }
        if(userService.checkAdminRole(user).isSuccess()){
            return productService.saveOrUpdateProduct(product);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @ResponseBody
    @RequestMapping(value = "set_salt_status.do")
    public ServerResponse<String> setSaleStatus(HttpSession session, Product product, Integer status){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),
                    ResponseCode.NEED_LOGIN.getDesc());
        }
        if(userService.checkAdminRole(user).isSuccess()){
            return productService.setSaleStatus(product.getId(), status);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }
}
