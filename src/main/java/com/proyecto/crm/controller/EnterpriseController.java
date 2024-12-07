package com.proyecto.crm.controller;

import com.proyecto.crm.response.CustomResponse;
import com.proyecto.crm.services.interfaces.IServiceEnterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("enterprise")
public class EnterpriseController {
    private final IServiceEnterprise serviceEnterprise;

    @Autowired
    public EnterpriseController(IServiceEnterprise serviceEnterprise) {
        this.serviceEnterprise = serviceEnterprise;
    }

    @GetMapping
    public String getEnterprise(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @RequestParam(value = "apiKey", required = false) String apiKey,
            Model model) {

        // Determinar la API Key a usar
        String apiKeyToUse = apiKey != null ? apiKey :
                (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")
                        ? authorizationHeader.substring(7)
                        : null);

        if (apiKeyToUse != null) {

            System.out.println("API Key recibida: " + apiKeyToUse);
            CustomResponse response = serviceEnterprise.getEnterprise(apiKey);
            model.addAttribute("lstEnterprise" ,response.getLstValue());
            //return "redirect:/enterprise";

        } else {
            System.out.println("No se recibió una API Key.");
        }

        return "enterprise";
    }
}
