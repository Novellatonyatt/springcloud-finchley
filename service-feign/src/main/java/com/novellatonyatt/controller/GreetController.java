package com.novellatonyatt.controller;

import com.novellatonyatt.feign.HiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Zhuang HaoTang
 * @create: 2020-05-10 17:01
 * @description:
 */
@RestController
public class GreetController {

    @Autowired
    private HiApi hiApi;

    @GetMapping("greet")
    public String greet(String name) {
        return hiApi.hi(name);
    }

}
