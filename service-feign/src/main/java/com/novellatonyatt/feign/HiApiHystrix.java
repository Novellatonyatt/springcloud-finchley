package com.novellatonyatt.feign;

import org.springframework.stereotype.Service;

/**
 * @author: Zhuang HaoTang
 * @create: 2020-05-15 11:06
 * @description:
 */
@Service
public class HiApiHystrix implements HiApi {

    @Override
    public String hi(String name) {
        return "sorry," + name;
    }
}
