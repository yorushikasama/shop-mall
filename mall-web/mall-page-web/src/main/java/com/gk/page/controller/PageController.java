package com.gk.page.controller;

import com.gk.page.service.PageService;
import com.gk.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(value = "/page")
public class PageController {

    @Autowired
    private PageService pageService;

    /****
     * 生成静态页
     */
    @GetMapping(value = "/{id}")
    public RespResult html(@PathVariable(value = "id")String id) throws Exception {
        pageService.html(id);
        return RespResult.ok();
    }
}
