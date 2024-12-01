package com.proyecto.crm.controller;

import com.proyecto.crm.entity.ServicesApiKey;
import com.proyecto.crm.response.CustomResponse;
import com.proyecto.crm.services.clases.ServiceApiKey;
import com.proyecto.crm.services.interfaces.IServiceApiKey;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/confirmApiKey")
public class NavBarController {
    private final IServiceApiKey serviceApiKey;

    @Autowired
    public NavBarController(IServiceApiKey serviceApiKey) {
        this.serviceApiKey = serviceApiKey;
    }
    /*
    @ModelAttribute("apiKeyValidate")
    public ServicesApiKey addApiKeyValidate() {
        return new ServicesApiKey();
    }*/

    @GetMapping
    public String showForm() {
        return "navbar";
    }

    @PostMapping
    public String validateApiKey(@ModelAttribute("apiKey") ServicesApiKey serviceApiKeyEntity, @RequestParam(value = "redirectUrl", required = false) String redirectUrl,
                                 HttpServletRequest httpRequest){
        CustomResponse response = serviceApiKey.validateApiKey(serviceApiKeyEntity.getApiKey());
        String baseUrl = httpRequest.getRequestURL().toString();
        baseUrl = baseUrl.substring(0, baseUrl.indexOf(httpRequest.getRequestURI()));
        if(response.getStatusCode() == 200){
            System.out.println("API Key validada correctamente");
            httpRequest.getSession().setAttribute("apiKey", serviceApiKeyEntity.getApiKey());
            String finalUrl = baseUrl + redirectUrl + "?apiKey=" + serviceApiKeyEntity.getApiKey();
            return "redirect:" + finalUrl;

        } else {
            System.out.println("API Key incorrecta");
            return "redirect:/confirmApiKey?ok";
        }

    }
}
