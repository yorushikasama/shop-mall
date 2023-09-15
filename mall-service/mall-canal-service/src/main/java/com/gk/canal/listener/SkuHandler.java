package com.gk.canal.listener;

import com.alibaba.fastjson.JSON;
import com.gk.goods.model.Sku;
import com.gk.page.feign.PageFeign;
import com.gk.search.feign.SkuSearchFeign;
import com.gk.search.model.SkuEs;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@CanalTable("sku")
@Component
public class SkuHandler implements EntryHandler<Sku> {
    @Autowired
    private SkuSearchFeign skuSearchFeign;

    @Autowired
    private PageFeign pageFeign;


    /**
     * 增加产品
     * @param sku
     */
    @SneakyThrows
    public void insert(Sku sku){
        if(sku.getStatus().intValue() == 1){
            System.out.println("新增产品");
            //导入索引
            skuSearchFeign.add(JSON.parseObject(JSON.toJSONString(sku),SkuEs.class));
        }
        //生成静态页
        pageFeign.html(sku.getSpuId());
    }

    /**
     * 修改
     * @param before
     * @param after
     */
    @SneakyThrows
    public void update(Sku before,Sku after){
        if(after.getStatus().intValue() == 2){
            //删除索引
            System.out.println("删除产品");
            skuSearchFeign.del(after.getId());
        }else {
            System.out.println("更新产品");
            skuSearchFeign.add(JSON.parseObject(JSON.toJSONString(after), SkuEs.class));
        }
        //生成静态页面
        pageFeign.html(after.getSpuId());
    }

    /**
     * 删除
     * @param sku
     */
    public void delete(Sku sku){
        System.out.println("删除产品");
        skuSearchFeign.del(sku.getId());
    }
}
