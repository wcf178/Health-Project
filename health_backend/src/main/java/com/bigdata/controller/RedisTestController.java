package com.bigdata.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bigdata.service.RedisTest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/redistest")
public class RedisTestController {
    @Reference
    private RedisTest redisTest;
    @RequestMapping("/init")
    public List<String> init(){
        System.out.println("\n\n"+redisTest);
        return redisTest.getProvinceList();
    }
}