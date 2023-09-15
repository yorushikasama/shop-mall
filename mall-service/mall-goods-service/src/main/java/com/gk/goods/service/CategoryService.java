package com.gk.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gk.goods.model.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {

    /**
     * 根据父ID查询子分类
     * @param id
     * @return
     */
    List<Category> queryByParentId(Integer id);
}
