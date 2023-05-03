package com.atguigu.controller;


import com.atguigu.entity.BaseCategory1;
import com.atguigu.entity.BaseCategory2;
import com.atguigu.entity.BaseCategory3;
import com.atguigu.result.RetVal;
import com.atguigu.service.BaseCategory1Service;
import com.atguigu.service.BaseCategory2Service;
import com.atguigu.service.BaseCategory3Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 一级分类表 前端控制器
 * </p>
 *
 * @author 林沛钿
 * @since 2023-04-29
 */
@RestController
@RequestMapping("/product")
@CrossOrigin
public class BaseCategory1Controller {
    private final BaseCategory1Service category1service;

    public BaseCategory1Controller(BaseCategory1Service category1service) {
        this.category1service = category1service;
    }
    @Autowired
    private BaseCategory2Service category2Service;
    @Autowired
    private BaseCategory3Service category3Service;


    @GetMapping("getCategory1")//http://127.0.0.1/product/getCategory1
    public RetVal getCategory1(){
        List<BaseCategory1> list = category1service.list(null);
        return RetVal.ok(list);
    }
    //http://127.0.0.1:8000/product/getCategory2/2
    @GetMapping("getCategory2/{category1Id}")//http://127.0.0.1/product/getCategory2
    public RetVal getCategory2(@PathVariable int category1Id){
        LambdaQueryWrapper<BaseCategory2> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BaseCategory2::getCategory1Id,category1Id);
        List<BaseCategory2> category2List = category2Service.list(wrapper);
        return RetVal.ok(category2List);
    }
    //http://127.0.0.1:8000/product/getCategory3/13
    @GetMapping("getCategory3/{category2Id}")//http://127.0.0.1/product/getCategory2
    public RetVal getCategory3(@PathVariable int category2Id){
        LambdaQueryWrapper<BaseCategory3> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BaseCategory3::getCategory2Id,category2Id);
        List<BaseCategory3> category3List = category3Service.list(wrapper);
        return RetVal.ok(category3List);
    }

}

