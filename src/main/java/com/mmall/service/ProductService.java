package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;

/**
 * 商品service接口
 */
public interface ProductService {

    /**
     * 新增或更新产品
     * @param product
     * @return
     */
    ServerResponse saveOrUpdateProduct(Product product);

    /**
     * 更新产品销售状态
     * @param productId
     * @return
     */
    ServerResponse setSaleStatus(Integer productId, Integer status);
}
