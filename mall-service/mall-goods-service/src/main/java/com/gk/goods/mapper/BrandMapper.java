package com.gk.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gk.goods.model.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface BrandMapper extends BaseMapper<Brand> {
    /**
     * 根据分类ID查询品牌集合
     * @param id
     * @return
     */
    @Select("select brand_id from category_brand where category_id=#{id}")
    List<Integer> queryBrandIds(Integer id);
}
