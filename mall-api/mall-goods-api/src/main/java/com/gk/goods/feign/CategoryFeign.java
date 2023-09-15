package com.gk.goods.feign;

import com.gk.goods.model.Category;
import com.gk.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("mall-goods")
public interface CategoryFeign {
    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @GetMapping("/category/{id}")
    RespResult<Category> one(@PathVariable("id")Integer id);
}
