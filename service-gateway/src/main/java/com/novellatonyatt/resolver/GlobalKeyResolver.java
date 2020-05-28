package com.novellatonyatt.resolver;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author: Zhuang HaoTang
 * @create: 2020-05-25 15:59
 * @description:
 */
@Component
public class GlobalKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just("Global");
    }
}
