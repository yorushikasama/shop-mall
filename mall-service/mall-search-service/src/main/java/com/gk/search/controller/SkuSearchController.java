package com.gk.search.controller;

import com.gk.search.mapper.SkuSearchMapper;
import com.gk.search.model.SkuEs;
import com.gk.search.service.SkuSearchService;
import com.gk.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/search")
public class SkuSearchController {

    @Autowired
    private SkuSearchService skuSearchService;


    /***
     * 商品搜索
     */
    @GetMapping
    public RespResult<Map<String,Object>> search(@RequestParam(required = false)Map<String,Object> searchMap){
        Map<String, Object> resultMap = skuSearchService.search(searchMap);
        return RespResult.ok(resultMap);
    }

    /*****
     * 增加索引
     */
    @PostMapping(value = "/add")
    public RespResult add(@RequestBody SkuEs skuEs){
        skuSearchService.add(skuEs);
        return RespResult.ok();
    }

    /***
     * 删除索引
     */
    @DeleteMapping(value = "/del/{id}")
    public RespResult del(@PathVariable(value = "id")String id){
        skuSearchService.del(id);
        return RespResult.ok();
    }
}

