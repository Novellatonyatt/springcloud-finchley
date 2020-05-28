package com.novellatonyatt.controller;

import com.novellatonyatt.service.GreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Zhuang HaoTang
 * @create: 2020-05-10 16:27
 * @description:
 */
@RestController
public class GreetController {

    @Autowired
    private GreetService greetService;

    @GetMapping("greet")
    public String greet(String name) {
        return greetService.greet(name);
    }

}
