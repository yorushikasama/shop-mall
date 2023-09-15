package com.gk.goods.controller;


import com.gk.goods.model.Product;
import com.gk.goods.service.SpuService;
import com.gk.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("spu")
public class SpuController {
    @Autowired
    private SpuService spuService;

    /**
     * 保存
     * @param product
     * @return
     */
    @PostMapping("/save")
    public RespResult save(@RequestBody Product product){
        //保存
        spuService.saveProduct(product);
        return RespResult.ok();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @GetMapping("/product/{id}")
    public RespResult<Product> one(@PathVariable("id")String id){
        Product product = spuService.findBySpuId(id);
        return RespResult.ok(product);
    }
}
