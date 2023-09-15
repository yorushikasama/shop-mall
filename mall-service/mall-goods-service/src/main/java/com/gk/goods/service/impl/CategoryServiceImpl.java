package com.gk.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gk.goods.mapper.CategoryMapper;
import com.gk.goods.model.Category;
import com.gk.goods.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据父ID查询子分类
     * @param pid
     * @return
     */
    public List<Category> queryByParentId(Integer pid) {
        //条件封装
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",pid);
        return categoryMapper.selectList(queryWrapper);
    }
}
