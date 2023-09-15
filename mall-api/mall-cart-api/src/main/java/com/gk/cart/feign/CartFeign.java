package com.gk.cart.feign;


import com.gk.cart.model.Cart;
import com.gk.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("mall-cart")
public interface CartFeign {

    /**
     * 购物车数据
     * @param ids
     * @return
     */
    @PostMapping("/cart/list")
    RespResult<List<Cart>> list(@RequestBody List<String> ids);

    /**
     * 删除指定购物车
     * @param ids
     * @return
     */
    @DeleteMapping("/cart")
    RespResult delete(@RequestBody List<String> ids);
}
