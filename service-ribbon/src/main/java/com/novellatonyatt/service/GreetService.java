package com.novellatonyatt.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author: Zhuang HaoTang
 * @create: 2020-05-10 16:20
 * @description:
 */
@Service
public class GreetService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "greetError")
    public String greet(String name) {
        return restTemplate.getForObject("http://service-hi/hi?name=" + name, String.class);
    }

    public String greetError(String name) {
        return "hi," + name + ",sorry,error";
    }
}
