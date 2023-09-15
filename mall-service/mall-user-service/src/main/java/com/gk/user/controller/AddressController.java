package com.gk.user.controller;

import com.gk.user.model.Address;
import com.gk.user.service.AddressService;
import com.gk.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 用户地址列表查询
     * @return
     */
    @GetMapping("/list")
    public RespResult<List<Address>> list(){
        String userName = "gp";
        List<Address> addresses = addressService.list(userName);
        return RespResult.ok(addresses);
    }
}
