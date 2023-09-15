package com.gk.goods.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gk.goods.model.SkuAttribute;

import java.util.List;

public interface SkuAttributeService extends IService<SkuAttribute> {

    /**
     * 根据分类ID查询属性集合
     * @param id
     * @return
     */
    List<SkuAttribute> queryList(Integer id);
}
