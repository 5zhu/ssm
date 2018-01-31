package com.mmall.service.impl;

import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.controller.backend.ProductManageController;
import com.mmall.dao.ProductMapper;
import com.mmall.pojo.Product;
import com.mmall.service.ProductService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ServerResponse saveOrUpdateProduct(Product product) {
        if(product != null){
            if(StringUtils.isNotBlank(product.getSubImages())){
                String[] subImageArray = product.getSubImages().split(",");
                if(subImageArray.length > 0){
                     product.setMainImage(subImageArray[0]);
                }
            }

            if(product.getId() != null){
                int rowCount = productMapper.updateByPrimaryKey(product);
                if(rowCount > 0){
                    return ServerResponse.createBySuccessMessage("更新产品成功");
                }
                return ServerResponse.createByErrorMessage("更新产品失败");
            }
        }else{
            int rowCount = productMapper.insert(product);
            if(rowCount > 0){
                return ServerResponse.createBySuccessMessage("新增产品成功");
            }
            return ServerResponse.createByErrorMessage("新增产品失败");
        }
        return ServerResponse.createByErrorMessage("参数错误");
    }

    @Override
    public ServerResponse setSaleStatus(Integer productId, Integer status) {
        if(productId ==null || status == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKey(product);
        if(rowCount > 0){
            return ServerResponse.createBySuccessMessage("更新销售状态成功");
        }
        return ServerResponse.createByErrorMessage("更新销售状态失败");
    }
}
