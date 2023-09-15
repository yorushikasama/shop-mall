package com.gk.search.feign;

import com.gk.search.model.SkuEs;
import com.gk.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "mall-search")
public interface SkuSearchFeign {

    /***
     * 商品搜索
     */
    @GetMapping("/search")
    RespResult<Map<String,Object>> search(@RequestParam(required = false)Map<String,Object> searchMap);

    /*****
     * 增加索引
     */
    @PostMapping(value = "/search/add")
    RespResult add(@RequestBody SkuEs skuEs);

    /***
     * 删除索引
     */
    @DeleteMapping(value = "/search/del/{id}")
    RespResult del(@PathVariable(value = "id")String id);
}