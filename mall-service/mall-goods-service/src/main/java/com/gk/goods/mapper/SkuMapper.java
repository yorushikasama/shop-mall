package com.gk.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gk.goods.model.Sku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;


public interface SkuMapper extends BaseMapper<Sku> {

    //库存递减
    @Update("update sku set num=num-#{num} where id=#{id} and num>=#{num}")
    int decount(@Param("id")String skuId,@Param("num")Integer num);
}
