package com.novellatonyatt.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: Zhuang HaoTang
 * @create: 2020-05-10 16:58
 * @description:
 */
@FeignClient(value = "service-hi", fallback = HiApiHystrix.class)
public interface HiApi {

    @RequestMapping(value = "hi", method = RequestMethod.GET)
    String hi(@RequestParam String name);

}
