package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.List;

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


    /**
     * 查询子节点，不递归
     * @param categoryId
     * @return
     */
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    /**
     * 递归查询子节点
     * @param categoryId
     * @return
     */
    ServerResponse getCategoryAndChildrenById(Integer categoryId);
}
