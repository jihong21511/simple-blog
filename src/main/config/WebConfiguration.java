package com.example.blog.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
    }
}
