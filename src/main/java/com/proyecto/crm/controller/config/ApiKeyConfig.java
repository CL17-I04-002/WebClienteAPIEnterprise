package com.proyecto.crm.controller.config;

import com.proyecto.crm.entity.ServicesApiKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiKeyConfig {
    @Bean
    public ServicesApiKey instanceSingletonServiceApiKey(){
        return new ServicesApiKey();
    }
}
