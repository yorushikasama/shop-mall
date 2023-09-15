package com.gk.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gk.goods.mapper.SkuAttributeMapper;
import com.gk.goods.model.SkuAttribute;
import com.gk.goods.service.SkuAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuAttributeServiceImpl extends ServiceImpl<SkuAttributeMapper, SkuAttribute> implements SkuAttributeService {

    @Autowired
    private SkuAttributeMapper skuAttributeMapper;
    /**
     * 根据分类ID查询属性集合
     * @param id
     * @return
     */
    public List<SkuAttribute> queryList(Integer id) {
        return skuAttributeMapper.queryByCategoryId(id);
    }
}
