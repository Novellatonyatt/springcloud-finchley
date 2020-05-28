package com.novellatonyatt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Zhuang HaoTang
 * @create: 2020-05-10 15:41
 * @description:
 */
@RestController
public class HiController {

    @Value("${server.port}")
    String port;

    @GetMapping("hi")
    public String hi(String name) {
        return String.format("welcome %s!，my port is :%s", name, port);
    }

}
