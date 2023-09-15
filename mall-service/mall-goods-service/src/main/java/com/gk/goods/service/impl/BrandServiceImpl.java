package com.gk.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gk.goods.mapper.BrandMapper;
import com.gk.goods.model.Brand;
import com.gk.goods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 多条件查询
     * @param brand
     * @return
     * like:表示模糊查询
     * eq:表示等值查询
     */

    public List<Brand> queryList(Brand brand) {
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(brand.getName())){
            queryWrapper.like("name",brand.getName());
        }
        if(!StringUtils.isEmpty(brand.getName())){
            queryWrapper.eq("initial",brand.getInitial());
        }
        return brandMapper.selectList(queryWrapper);
    }


    /**
     * 分页查询
     * @param currentPage
     * @param size
     * @param brand
     * @return
     * like:表示模糊查询
     */
    public Page<Brand> queryPageList(Long currentPage, Long size, Brand brand) {
        //封装查询条件
        Page<Brand> page = brandMapper.selectPage(
                new Page<Brand>(currentPage,size),
                new QueryWrapper<Brand>()
                        .like("name",brand.getName())
        );
        return page;
    }

    /**
     * 根据分类ID查询品牌
     * @param id
     * @return
     */
    public List<Brand> queryByCategoryId(Integer id) {
        //查询分类ID对应的品牌集合
        List<Integer> brandIds = brandMapper.queryBrandIds(id);
        //根据品牌ID集合查询品牌信息
        List<Brand> brands = brandMapper.selectBatchIds(brandIds);
        return brands;
    }


}
