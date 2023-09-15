package com.gk.goods.controller;


import com.gk.goods.model.SkuAttribute;
import com.gk.goods.service.SkuAttributeService;
import com.gk.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("skuAttribute")
@CrossOrigin
public class SkuAttributeController {

    @Autowired
    private SkuAttributeService skuAttributeService;

    /**
     * 根据分类ID查询
     * @param id
     * @return
     */
    @GetMapping("/category/{id}")
    public RespResult<SkuAttribute> categoryAttributeList(@PathVariable("id") Integer id){
        //根据分类ID查询属性参数
        List<SkuAttribute> skuAttributes = skuAttributeService.queryList(id);
        return RespResult.ok(skuAttributes);
    }
}
