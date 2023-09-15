package com.gk.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gk.goods.model.Sku;
import com.gk.goods.model.SkuAttribute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface SkuAttributeMapper extends BaseMapper<SkuAttribute> {

    /**
     * 根据分类ID查询属性集合
     * @param id
     * @return
     */
    @Select("select * from sku_attribute where id in(select attr_id from category_attr where category_id=#{id})")
    List<SkuAttribute> queryByCategoryId(Integer id);
}
