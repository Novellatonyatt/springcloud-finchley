package com.novellatonyatt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Zhuang HaoTang
 * @create: 2020-05-22 16:04
 * @description:
 */
@RestController
public class FallbackController {

    @RequestMapping("fallback")
    public String fallback() {
        return "error";
    }

}
