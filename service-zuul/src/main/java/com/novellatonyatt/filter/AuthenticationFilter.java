package com.novellatonyatt.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: Zhuang HaoTang
 * @create: 2020-05-15 15:51
 * @description:
 */
@Component
public class AuthenticationFilter extends ZuulFilter {

    /**
     * 设置过滤器的类型
     * pre:路由前
     * routing:路由中
     * post:路由后
     * error:发生异常时
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 设置过滤器的顺序
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 设置当满足什么条件时进入过滤器
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器处理逻辑
     */
    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String accessToken = request.getParameter("access_token");
        if (accessToken == null) { // 如果请求不存在access_token参数，那么返回401状态码，响应体为token is empty
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            try {
                requestContext.getResponse().getWriter().write("token is empty");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
