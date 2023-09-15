package com.gk.cart.controller;

import com.gk.cart.model.Cart;
import com.gk.cart.service.CartService;
import com.gk.util.RespResult;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;

    /***
     * 添加购物车
     * id: skuid
     */
    @GetMapping(value = "/{id}/{num}")
    public RespResult add(@PathVariable("id")String id,
                          @PathVariable("num")Integer num){
        //用户名字
        String userName="gp";
        //加入购物车
        cartService.add(id,userName,num);
        return RespResult.ok();
    }

    /**
     * 购物车列表
     * @return
     */
    @GetMapping("/list")
    public RespResult<List<Cart>> list(){
        String userName = "gp";
        List<Cart> carts = cartService.list(userName);
        return RespResult.ok(carts);
    }

    /**
     * 购物车数据
     * @param ids
     * @return http://localhost:8087/cart/list
     */
    @PostMapping("/list")
    public RespResult<List<Cart>> list(@RequestBody List<String> ids){
        //购物车集合
        ArrayList<Cart> carts = Lists.newArrayList(cartService.list(ids));
        return RespResult.ok(carts);
    }

    /**
     * 删除指定购物车
     * @param ids
     * @return
     */
    @DeleteMapping
    public RespResult delete(@RequestBody List<String> ids){
        //删除购物车集合
        cartService.delete(ids);
        return RespResult.ok();
    }
}