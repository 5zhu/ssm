package com.mmall.service;

import com.mmall.common.ServerResponse;
import org.springframework.stereotype.Service;

/**
 * CategoryService接口
 */
public interface CategoryService {

    /**
     * 添加category
     * @param categoryName
     * @param parentId
     * @return
     */
    ServerResponse addCategory(String categoryName, Integer parentId);

    /**
     * 更新category 的名称
     * @param categoryId
     * @param categoryName
     * @return
     */
    ServerResponse updateCategory(Integer categoryId, String categoryName);


}
