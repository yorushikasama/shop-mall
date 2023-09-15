package com.gk.canal.listener;

import com.gk.goods.feign.SkuFeign;
import com.gk.goods.model.AdItems;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@Component
@CanalTable(value = "ad_items")
@Log
public class AdItemsHandler implements EntryHandler<AdItems> {
    @Autowired
    private SkuFeign skuFeign;

    /****
     * 数据库增加数据，执行该方法
     * @param adItems
     */
    public void insert(AdItems adItems) {
        //加载缓存
        System.out.println("加载缓存");
        skuFeign.updateTypeItems(adItems.getType());
    }

    /****
     * 数据库修改数据，执行该方法
     */
    public void update(AdItems before, AdItems after) {
        //分类不同，则重新加载之前的缓存
        if(before.getType().intValue() != after.getType().intValue()){
            //修改缓存
            System.out.println("修改缓存");
            skuFeign.updateTypeItems(before.getType());
        }
        System.out.println(before+":"+after);
        //加载缓存
        skuFeign.updateTypeItems(after.getType());
    }

    /****
     * 数据库删除数据，执行该方法
     */
    public void delete(AdItems adItems) {
        //删除缓存
        System.out.println("删除缓存");
        skuFeign.deleteTypeItems(adItems.getType());
    }
}
