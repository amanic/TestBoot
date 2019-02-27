package com.cht.testspringboot.configuration;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @auther chen.haitao
 * @date 2019-02-27
 */
@Component
@Order(value = 3)
public class CORESFilter implements Filter {
    private final Set<String> ALLOWED_DOMAINS = new HashSet<String>() {
        {
            add(".weidai.com.cn");
            add(".wd5.com.cn");
            add(".wdai.com");
            add(".wdgood.cn");
            add(".weidai.work");
            add(".wdtest.cc");
            add(".wddev.cc");
            //add("192.168.91.229");
            add("192.168.2.215");
            add("192.168.90.49");
            add("192.168.91.65");
            add("192.168.91.204");
            add("192.168.91.166");
            add("192.168.92.203");
            add("192.168.90.192");
            add("192.168.2.119");
            add("0.0.0.0");
            add("localhost");
            add("172.20.10.2");
            add(".weidaixd.com");
            add("192.168.90.97");
            add("clapp-app.wdai.com");
            add("47.95.6.88");
            add("192.168.0.103");
            add("192.168.91.235");
            add("192.168.90.92");
            add("192.168.91.68");
            add("192.168.91.228");
            add("192.168.90.246");
            add("192.168.91.193");
            add("192.168.90.219");
            add("192.168.90.197");


        }
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String allowedDomains = filterConfig.getInitParameter("allowedDomains");
        if (allowedDomains != null) {
            ALLOWED_DOMAINS.addAll(Arrays.asList(allowedDomains.split(",")));
        }
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (allowCors(request)) {
            HttpServletRequest servletRequest = (HttpServletRequest) request;
            HttpServletResponse servletResponse = (HttpServletResponse) response;
            //servletResponse.addHeader("Access-Control-Allow-Origin", "*");
            servletResponse.setHeader("Access-Control-Allow-Origin", servletRequest.getHeader("Origin"));//必填
            servletResponse.setHeader("Access-Control-Allow-Methods", servletRequest.getHeader("Access-Control-Request-Method"));//可选
            servletResponse.setHeader("Access-Control-Allow-Headers", servletRequest.getHeader("Access-Control-Request-Headers"));//可选
            servletResponse.setHeader("Access-Control-Allow-Credentials", "true");//可选
            servletResponse.setHeader("Access-Control-Max-Age", getCorsMaxAge(request));//可选，指定本次预检请求的有效期，单位为秒,我先写个1天
        }
        chain.doFilter(request, response);
    }
    //可继承改写，根据自己情况设置哪些源地址、目标url允许跨域
    protected boolean allowCors(ServletRequest request) {
        String originDomain = getHostName(((HttpServletRequest) request).getHeader("Origin"));
        if (originDomain != null) {
//            return true;
            for (String domain : ALLOWED_DOMAINS) {
                if (originDomain.endsWith(domain)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    private String getHostName(String url) {
        if (url == null || url.length() == 0) {
            return null;
        }
        try {
            return new URL(url).getHost();
        } catch (MalformedURLException e) {
            return null;
        }
    }

    //可继承改写，自己设置有效期
    protected String getCorsMaxAge(ServletRequest request) {
        return "86400";
    }

    @Override
    public void destroy() {
    }
}