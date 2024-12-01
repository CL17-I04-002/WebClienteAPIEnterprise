package com.proyecto.crm.controller;

import com.proyecto.crm.controller.config.ApiKeyConfig;
import com.proyecto.crm.entity.ServicesApiKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalAttributesController {
    private final ServicesApiKey  servicesApiKey;

    @Autowired
    public GlobalAttributesController(ServicesApiKey servicesApiKey) {
        this.servicesApiKey = servicesApiKey;
    }

    // Este método asegura que apiKeyValidate esté disponible en todas las vistas
    @ModelAttribute("apiKeyValidate")
    public ServicesApiKey addApiKeyValidate(){
        return servicesApiKey;
    }

}
