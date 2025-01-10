package com.proyecto.crm.controller;

import com.proyecto.crm.services.interfaces.IServiceApiKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
/*@RequestMapping("/confirmApiKey")*/
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

    /*@PostMapping
    public String validateApiKey(@ModelAttribute("apiKey") ServicesApiKey serviceApiKeyEntity, @RequestParam(value = "redirectUrl", required = false) String redirectUrl,
                                 HttpServletRequest httpRequest){
        CustomResponse response = serviceApiKey.validateApiKey(serviceApiKeyEntity.getApiKey());
        String baseUrl = httpRequest.getRequestURL().toString();

        if(response.getStatusCode() == 200){
            System.out.println("API Key validada correctamente");
            baseUrl = baseUrl.substring(0, baseUrl.indexOf(httpRequest.getRequestURI()));
            httpRequest.getSession().setAttribute("apiKey", serviceApiKeyEntity.getApiKey());
            String finalUrl = baseUrl + redirectUrl + "?apiKey=" + serviceApiKeyEntity.getApiKey();
            return "redirect:" + finalUrl;

        } else if(response.getStatusCode() == 404){
            System.out.println("API Key no encontrada");
            baseUrl = httpRequest.getRequestURL().toString().concat("?error");
            return "redirect:" + baseUrl;
        }
        else {
            System.out.println("API Key incorrecta");
            return "redirect:/confirmApiKey?ok";
        }

    }*/
}
