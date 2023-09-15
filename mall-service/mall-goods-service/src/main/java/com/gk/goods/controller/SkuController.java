package com.gk.goods.controller;

import com.gk.goods.model.Sku;
import com.gk.goods.service.SkuService;
import com.gk.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sku")
@CrossOrigin
public class SkuController {
    @Autowired
    private SkuService skuService;


    /**
     * 指定分类下的推广产品列表
     * @param id
     * @return
     */
    @GetMapping("/aditems/type")
    public List<Sku> typeItems(@RequestParam("id")Integer id){
        //查询
        List<Sku> adSkuItems = skuService.typeSkuItems(id);
        return adSkuItems;
    }

    /**
     * 删除指定分类下的推广产品列表
     * @param id
     * @return
     */
    @DeleteMapping("/aditems/type")
    public RespResult deleteTypeItems(@RequestParam("id") Integer id){
        //清理缓存
        skuService.delTypeSkuItems(id);
        return RespResult.ok();
    }

    /****
     * 根据推广分类查询推广产品列表
     *
     */
    @PutMapping( "/aditems/type")
    public RespResult updateTypeItems(@RequestParam("id")Integer id){
        //修改
        skuService.updateTypeSkuItems(id);
        return RespResult.ok();
    }
}
