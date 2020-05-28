package com.novellatonyatt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Zhuang HaoTang
 * @create: 2020-05-23 16:20
 * @description:
 */
@RestController
public class ConfigController {

    @Value("${foo}")
    private String foo;

    @RequestMapping("getConfig")
    public Map getConfig() {
        Map<String, String> config = new HashMap<>();
        config.put("foo", foo);
        return config;
    }
}
