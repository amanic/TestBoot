package com.cht.testspringboot.configuration;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @auther chen.haitao
 * @date 2019-03-06
 */
@Component
public class MyServletContext implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("attribute","value");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
