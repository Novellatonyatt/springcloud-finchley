package com.novellatonyatt.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author: Zhuang HaoTang
 * @create: 2020-05-25 13:42
 * @description:
 */
@Component
public class RequestTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestTimeGatewayFilterFactory.Config> {

    private static final Logger logger = LoggerFactory.getLogger(RequestTimeFilter.class);

    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";

    /**
     * 构建方法必须把接收参数的Entity的类类型传入到父类的构造方法中
     */
    public RequestTimeGatewayFilterFactory() {
        super(Config.class);
    }

    /**
     * 结合GatewayFilter的参数来实现过滤器
     */
    @Override
    public GatewayFilter apply(Config config) {
        GatewayFilter gatewayFilter = (exchange, chain) -> {
            // 路由前
            exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
            return chain.filter(exchange).then( // 路由后
                    Mono.fromRunnable(() -> {
                        Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                        if (startTime != null) {
                            StringBuilder sb = new StringBuilder(exchange.getRequest().getURI().getRawPath()).append(": ").append(System.currentTimeMillis() - startTime).append("ms");
                            if (config.isWithParameter()) {
                                sb.append(" params:").append(exchange.getRequest().getQueryParams());
                            }
                            logger.info(sb.toString());
                        }
                    })
            );
        };
        return gatewayFilter;
    }

    /**
     * 定义用于接收GatewayFilter参数的Entity
     */
    public static class Config {

        private boolean withParameter;

        public boolean isWithParameter() {
            return withParameter;
        }

        public void setWithParameter(boolean withParameter) {
            this.withParameter = withParameter;
        }
    }

}
