package com.proyecto.crm.controller;

import com.proyecto.crm.entity.ServicesApiKey;
import com.proyecto.crm.response.CustomResponse;
import com.proyecto.crm.services.interfaces.IServiceApiKey;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/index")
public class IndexController {

    private final IServiceApiKey serviceApiKey;

    @Autowired
    public IndexController(IServiceApiKey serviceApiKey) {
        this.serviceApiKey = serviceApiKey;
    }
    @GetMapping
    public String showForm() {
        return "index";
    }

     @PostMapping
    public String validateApiKey(@ModelAttribute("apiKey") ServicesApiKey serviceApiKeyEntity,
                                 HttpServletRequest httpRequest){
        CustomResponse response = serviceApiKey.validateApiKey(serviceApiKeyEntity.getApiKey());
        String baseUrl = httpRequest.getRequestURL().toString();

        if(response.getStatusCode() == 200){
            System.out.println("API Key validada correctamente");
            httpRequest.getSession().setAttribute("apiKey", serviceApiKeyEntity.getApiKey());
            String finalUrl = baseUrl + "?apiKey=" + serviceApiKeyEntity.getApiKey();
            return "redirect:" + finalUrl;

        } else if(response.getStatusCode() == 404){
            System.out.println("API Key no encontrada");
            baseUrl = httpRequest.getRequestURL().toString().concat("?error");
            return "redirect:" + baseUrl;
        }
        else {
            System.out.println("API Key incorrecta");
            return "redirect:/index?error";
        }

    }
}
