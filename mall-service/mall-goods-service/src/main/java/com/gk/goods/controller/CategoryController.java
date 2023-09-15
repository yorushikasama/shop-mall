package com.gk.goods.controller;


import com.gk.goods.model.Category;
import com.gk.goods.service.CategoryService;
import com.gk.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/parent/{pid}")
    public RespResult<List<Category>> list(@PathVariable("pid") Integer pid){
        List<Category> categories = categoryService.queryByParentId(pid);
        return RespResult.ok(categories);
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public RespResult<Category> one(@PathVariable("id")Integer id){
        Category category = categoryService.getById(id);
        return RespResult.ok(category);
    }

}
