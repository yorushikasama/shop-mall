package com.gk.goods.feign;

import com.gk.goods.model.Product;
import com.gk.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("mall-goods")
public interface SpuFeign {

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @GetMapping("/spu/product/{id}")
    RespResult<Product> one(@PathVariable("id")String id);
}
