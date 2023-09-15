package com.gk.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gk.goods.model.Product;
import com.gk.goods.model.Spu;

public interface SpuService extends IService<Spu> {

    /**
     * 保存商品
     * @param product
     */
    void saveProduct(Product product);

    /**
     * 查询商品详情
     * @param id
     * @return
     */
    Product findBySpuId(String id);
}
