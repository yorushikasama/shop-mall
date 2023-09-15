package com.gk.goods.feign;

import com.gk.goods.model.Sku;
import com.gk.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("mall-goods")
public interface SkuFeign {

    /**
     * 指定分类下的推广产品列表
     * @param id
     * @return
     */
    @GetMapping("/sku/aditems/type")
    public List<Sku> typeItems(@RequestParam("id")Integer id);

    /**
     * 删除指定分类下的推广产品列表
     * @param id
     * @return
     */
    @DeleteMapping("/sku/aditems/type")
    public RespResult deleteTypeItems(@RequestParam("id")Integer id);

    /**
     * 修改指定分类下的推广产品列表
     * @param id
     * @return
     */
    @PutMapping("/sku/aditems/type")
    public RespResult updateTypeItems(@RequestParam("id") Integer id);
}
