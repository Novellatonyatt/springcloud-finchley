package com.novellatonyatt;

import com.novellatonyatt.filter.RequestTimeFilter;
import com.novellatonyatt.resolver.GlobalKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(predicateSpec -> predicateSpec.path("/api-ribbon/*").filters(filter -> filter.stripPrefix(1).hystrix(config -> config.setName("hy1").setFallbackUri("forward:/fallback")).requestRateLimiter().rateLimiter(RedisRateLimiter.class, config -> config.setBurstCapacity(3).setReplenishRate(1)).configure(config -> config.setKeyResolver(new GlobalKeyResolver()))).uri("lb://service-ribbon"))
                .route(predicateSpec -> predicateSpec.path("/api-feign/*").filters(filter -> filter.stripPrefix(1).hystrix(config -> config.setName("hy2").setFallbackUri("forward:/fallback")).requestRateLimiter().rateLimiter(RedisRateLimiter.class, config -> config.setBurstCapacity(3).setReplenishRate(1)).configure(config -> config.setKeyResolver(new GlobalKeyResolver()))).uri("lb://service-feign")).build();
    }

//    @Bean
//    public RouteLocator myRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(predicateSpec -> predicateSpec.path("/api-ribbon/*").filters(filter -> filter.filter(new RequestTimeFilter()).stripPrefix(1)).uri("lb://service-ribbon"))
//                .route(predicateSpec -> predicateSpec.path("/api-feign/*").filters(filter -> filter.filter(new RequestTimeFilter()).stripPrefix(1)).uri("lb://service-feign")).build();
//    }


}
