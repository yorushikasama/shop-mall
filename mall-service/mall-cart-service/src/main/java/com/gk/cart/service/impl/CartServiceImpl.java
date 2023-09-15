package com.gk.cart.service.impl;

import com.gk.cart.mapper.CartMapper;
import com.gk.cart.model.Cart;
import com.gk.cart.service.CartService;
import com.gk.goods.feign.SkuFeign;
import com.gk.goods.model.Sku;
import com.gk.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private MongoTemplate mongoTemplate;

    //加入购物车
    @Override
    public void add(String id,String userName,Integer num) {
        //删除购物车中当前商品
        cartMapper.deleteById(userName+id);

        if(num>0){
            //查询Sku
            RespResult<Sku> skuResp = skuFeign.one(id);
            //将Sku转换成Cart
            Cart cart = new Cart();
            cart.setUserName(userName);
            cart.setSkuId(id);
            cart.setNum(num);
            sku2cart(skuResp.getData(),cart);
            //批量增加
            cartMapper.save(cart);
        }
    }



    /***
     * Sku转Cart
     * @param sku
     * @param cart
     */
    public void sku2cart(Sku sku, Cart cart){
        cart.setImage(sku.getImage());
        cart.set_id(cart.getUserName()+cart.getSkuId());
        cart.setName(sku.getName());
        cart.setPrice(sku.getPrice());
        cart.setSkuId(sku.getId());
    }


    /**
     * 购物车列表
     * @param userName
     * @return
     */
    public List<Cart> list(String userName) {
        //查询条件
        Cart cart = new Cart();
        cart.setUserName(userName);
        return cartMapper.findAll(Example.of(cart), Sort.by("_id"));
    }

    /**
     * 根据ID集合查询购物车列表
     * @param ids
     * @return
     */
    public Iterable<Cart> list(List<String> ids) {
        return cartMapper.findAllById(ids);
    }

    /**
     * 根据ID删除
     * @param ids
     */
    public void delete(List<String> ids) {
        mongoTemplate.remove(Query.query(Criteria.where("_id").in(ids)),Cart.class);
    }
}