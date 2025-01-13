package com.proyecto.crm.controller.handler;

import com.proyecto.crm.entity.ServicesApiKey;
import com.proyecto.crm.services.clases.ServiceApiKey;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {
    private final ServicesApiKey servicesApiKey;
    private  final ServiceApiKey serviceApiKey;

    @Autowired
    public ApiKeyInterceptor(ServicesApiKey servicesApiKey, ServiceApiKey serviceApiKey){
        this.servicesApiKey = servicesApiKey;
        this.serviceApiKey = serviceApiKey;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String apiKey = request.getHeader("Authorization");
        if(apiKey != null && apiKey.startsWith("Bearer ")){
            apiKey = apiKey.substring(7);
        } else{
            apiKey = request.getParameter("apiKey");
        }

        if(apiKey != null && serviceApiKey.validateApiKey(apiKey).getStatusCode() == 200){
            request.setAttribute("apiKey", apiKey);
            return true;
        }
        if (request.getRequestURI().equals("/index")) {
            return true;  // No redirigir, ya estamos en la página de creación de la API Key
        }
        response.sendRedirect("/index");
        return false;
    }
}