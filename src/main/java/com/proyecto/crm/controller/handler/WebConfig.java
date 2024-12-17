package com.proyecto.crm.controller.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final ApiKeyInterceptor apiKeyInterceptor;

    @Autowired
    public WebConfig(ApiKeyInterceptor apiKeyInterceptor){
        this.apiKeyInterceptor = apiKeyInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiKeyInterceptor)
                .excludePathPatterns("/login", "/crearApiKey", "/index", "/navbar", "/css/**", "/js/**", "/images/**");
    }
}
