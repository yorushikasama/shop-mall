package com.gk.goods.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gk.goods.model.Brand;
import com.gk.goods.service.BrandService;
import com.gk.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 增加品牌
     * @param brand
     * @return
     */
    @PostMapping
    public RespResult add(@RequestBody Brand brand){
        //增加品牌
        brandService.save(brand);
        return RespResult.ok();
    }


    /**
     * 删除品牌
     * @param brand
     * @return
     */
    @DeleteMapping("/{id}")
    public RespResult update(@RequestBody Brand brand){
        //修改品牌
        brandService.updateById(brand);
        return RespResult.ok();
    }


    /**
     * 条件查询
     * @param brand
     * @return
     */
    @PostMapping("/search")
    public RespResult<List<Brand>> list(@RequestBody(required = false) Brand brand){
        //查询
        List<Brand> brands = brandService.queryList(brand);
        return RespResult.ok(brands);
    }


    @PostMapping("/search/{page}/{size}")
    public RespResult<Page<Brand>> list(
            @PathVariable(value = "page")Long currentPage,
            @PathVariable(value = "size")Long size,
            @RequestBody(required = false) Brand brand){
        //分页查询
        Page<Brand> brandPage = brandService.queryPageList(currentPage,size,brand);
        return RespResult.ok(brandPage);
    }


    /**
     * 根据分类ID查询品牌
     * @param id
     * @return
     */
    @GetMapping("/category/{id}")
    public RespResult<List<Brand>> categoryBrands(@PathVariable("id")Integer id){
        List<Brand> brands = brandService.queryByCategoryId(id);
        return RespResult.ok(brands);
    }



}
