package com.proyecto.crm.controller;

import com.proyecto.crm.entity.ServicesApiKey;
import com.proyecto.crm.response.CustomResponse;
import com.proyecto.crm.services.dto.ServiceApiKeyDto;
import com.proyecto.crm.services.interfaces.IServiceApiKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/crearApiKey")
public class ApiKeyController {

    private final IServiceApiKey apiKeyService;

    @Autowired
    public ApiKeyController(IServiceApiKey apiKeyService) {
        this.apiKeyService = apiKeyService;
    }
    @GetMapping
    public String getRegistrationForm(Model model){
        ServicesApiKey serviceApiKey = new ServicesApiKey();
        model.addAttribute("apiKey", serviceApiKey);
        return "crearApiKey";
    }
    @PostMapping
    public String crearApiKey(@ModelAttribute("apiKey") ServicesApiKey serviceApiKey, RedirectAttributes redirectAttributes){
        //serviceApiKey.se(true);
        CustomResponse customResponse = apiKeyService.createServiceApiKey(serviceApiKey);
        if(customResponse.getStatusCode() == 200){
            redirectAttributes.addFlashAttribute("showApiKey", customResponse.getMessage());
            return "redirect:/crearApiKey?exito";
        }else{
            redirectAttributes.addFlashAttribute("errorApiKey", "No se pudo registrar el API Key");
            return "redirect:/crearApiKey?error";
        }
    }
}
