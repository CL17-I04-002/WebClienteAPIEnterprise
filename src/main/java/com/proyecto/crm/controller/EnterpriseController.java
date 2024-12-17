package com.proyecto.crm.controller;

import com.proyecto.crm.entity.Enterprise;
import com.proyecto.crm.response.CustomResponse;
import com.proyecto.crm.services.interfaces.IServiceEnterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping
public class EnterpriseController {
    private final IServiceEnterprise serviceEnterprise;

    @Autowired
    public EnterpriseController(IServiceEnterprise serviceEnterprise) {
        this.serviceEnterprise = serviceEnterprise;
    }

    @GetMapping("/crearEnterprise")
    public String getCreateEnterprise(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                                      @RequestParam(value = "apiKey", required = false) String apiKey,
                                      @RequestParam(value = "enterpriseId", required = false) Long enterpriseId,
                                      Model model){
        CustomResponse response = new CustomResponse();
        Enterprise enterprise = new Enterprise();
        String apiKeyToUse = apiKey != null ? apiKey :
                            (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")
                            ? authorizationHeader.substring(7) : null);
        if (apiKeyToUse != null && enterpriseId != null){
            response = serviceEnterprise.getEnterprise(enterpriseId, apiKeyToUse);
        } else if(apiKeyToUse != null){
            response = serviceEnterprise.getEnterprise(null, apiKeyToUse);
        }
        if(response.getStatusCode() == 200 && enterpriseId != null) {
            enterprise = (Enterprise) response.getLstValue();
            model.addAttribute("enterprise", enterprise);
        }
        else if(response.getStatusCode() == 200){
            model.addAttribute("enterprise", enterprise);
        }
        return "crearEnterprise";
    }
    @PostMapping("/crearEnterprise")
    public String postCreateEnterprise(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                                       @RequestParam(value = "apiKey", required = false) String apiKey,
                                       @ModelAttribute Enterprise modelEnterprise){
        Enterprise enterprise = new Enterprise();
        CustomResponse customResponse = new CustomResponse();
        enterprise.setName(modelEnterprise.getName());
        enterprise.setContactEmail(modelEnterprise.getContactEmail());
        enterprise.setContactPhone(modelEnterprise.getContactPhone());
        enterprise.setAddress(modelEnterprise.getAddress());
        String apiKeyToUse = apiKey != null ? apiKey :
                (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")
                        ? authorizationHeader.substring(7) : null);
        if(apiKeyToUse != null && modelEnterprise.getId() != null){
            enterprise.setId(modelEnterprise.getId());
            customResponse = serviceEnterprise.updateEnterprise(apiKeyToUse, enterprise);
        } else if(apiKeyToUse != null){
            customResponse = serviceEnterprise.createEnterprise(apiKeyToUse, enterprise);
        }
        if(customResponse.getStatusCode() == 200){

        }
        return "redirect:/crearEnterprise?apiKey=" + apiKeyToUse;
    }
    @GetMapping("/eliminarEnterprise")
    public String deleteEnterprise(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                                           @RequestParam(value = "apiKey", required = false) String apiKey,
                                           @RequestParam(value = "enterpriseId", required = false) Long enterpriseId){
        CustomResponse customResponse = new CustomResponse();
        Enterprise enterprise = new Enterprise();
        enterprise.setId(enterpriseId);
        String apiKeyToUse = apiKey != null ? apiKey :
                (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")
                    ? authorizationHeader.substring(7) : null);
        if(apiKeyToUse != null && enterprise.getId() != null){
            customResponse = serviceEnterprise.deleteEnterprise(apiKeyToUse, enterprise);
        }
        if(customResponse.getStatusCode() == 200){

        }
        return "redirect:/enterprise?apiKey=" + apiKeyToUse;
    }

    /**
     * It gets API Key either query param or header through Authorization
     * @param authorizationHeader
     * @param apiKey
     * @param model
     * @return
     */
    @GetMapping("/enterprise")
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
            CustomResponse response = serviceEnterprise.getEnterprise(null, apiKeyToUse);
            model.addAttribute("lstEnterprise" ,response.getLstValue());

        } else {
            System.out.println("No se recibió una API Key.");
            return "enterprise";
        }
        return "enterprise";
    }
}
