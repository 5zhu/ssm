package com.mmall.service;

import com.mmall.common.ServerResponse;
import org.springframework.stereotype.Service;

/**
 * CategoryService接口
 */
public interface CategoryService {

    ServerResponse addCategory(String categoryName, Integer parentId);


}
