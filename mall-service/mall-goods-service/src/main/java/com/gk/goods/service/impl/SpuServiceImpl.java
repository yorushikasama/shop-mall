package com.gk.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gk.goods.mapper.BrandMapper;
import com.gk.goods.mapper.CategoryMapper;
import com.gk.goods.mapper.SkuMapper;
import com.gk.goods.mapper.SpuMapper;
import com.gk.goods.model.*;
import com.gk.goods.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 保存商品
     *
     * @param product
     */
    public void saveProduct(Product product) {
        //Spu
        Spu spu = product.getSpu();

        //如果ID为空，则增加
        if(StringUtils.isEmpty(spu.getId())){
            //上架
            spu.setIsMarketable(1);
            //未删除
            spu.setIsDelete(0);
            //状态
            spu.setStatus(1);
            //添加
            spuMapper.insert(spu);
        }else {
            //ID不为空，则修改
            spuMapper.updateById(spu);
            //删除之前的Sku记录
            skuMapper.delete(new QueryWrapper<Sku>().eq("spu_id",spu.getId()));
        }

        //查询三级分类
        Category category = categoryMapper.selectById(spu.getCategoryThreeId());
        //查询品牌
        Brand brand = brandMapper.selectById(spu.getBrandId());
        //当前时间
        Date now = new Date();


        //新增Sku集合
        for (Sku sku : product.getSkus()) {
            //设置名字
            String skuName = spu.getName();
            Map<String, String> attrMap = JSON.parseObject(sku.getSkuAttribute(), Map.class);
            for (Map.Entry<String, String> entry : attrMap.entrySet()) {
                skuName += "   "+entry.getValue();
            }
            sku.setName(skuName);
            //设置图片
            sku.setImage(spu.getImages());
            //设置状态
            sku.setStatus(1);
            //设置类目ID
            sku.setCategoryId(spu.getCategoryThreeId());
            //设置类目名称
            sku.setCategoryName(category.getName());
            //设置品牌ID
            sku.setBrandId(brand.getId());
            //设置品牌名称
            sku.setBrandName(brand.getName());
            //设置Spuid
            sku.setSpuId(spu.getId());
            //时间
            sku.setCreateTime(now);
            sku.setUpdateTime(now);
            //增加
            skuMapper.insert(sku);
        }
    }

    /**
     * 查询商品详情
     * @param id
     * @return
     */
    public Product findBySpuId(String id) {
        //查询Spu
        Spu spu = spuMapper.selectById(id);

        //查询Sku集合
        List<Sku> skus = skuMapper.selectList(new QueryWrapper<Sku>().eq("spu_id", id));
        Product product = new Product();
        product.setSpu(spu);
        product.setSkus(skus);
        return product;
    }
}
