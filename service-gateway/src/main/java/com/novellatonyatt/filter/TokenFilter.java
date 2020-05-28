package com.novellatonyatt.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author: Zhuang HaoTang
 * @create: 2020-05-25 14:39
 * @description:
 */
@Component
public class TokenFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 路由前
        String token = exchange.getRequest().getQueryParams().getFirst("access_token");
        if (token == null || token.isEmpty()) { // 如果请求不包含access_token参数，那么返回401状态码
            logger.info("token is empty...");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange); // 交由下一个过滤器处理
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
