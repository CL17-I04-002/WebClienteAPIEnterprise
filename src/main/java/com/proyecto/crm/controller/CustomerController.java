package com.proyecto.crm.controller;

import com.proyecto.crm.entity.Customer;
import com.proyecto.crm.entity.Enterprise;
import com.proyecto.crm.response.CustomResponse;
import com.proyecto.crm.services.interfaces.IServiceCustomer;
import com.proyecto.crm.services.interfaces.IServiceEnterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class CustomerController {

    private final IServiceCustomer serviceCustomer;
    private final IServiceEnterprise serviceEnterprise;

    @Autowired
    public CustomerController(IServiceCustomer serviceCustomer, IServiceEnterprise serviceEnterprise) {
        this.serviceCustomer = serviceCustomer;
        this.serviceEnterprise = serviceEnterprise;
    }
    @GetMapping("/crearCustomer")
    public String getCreateCustomer(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                              @RequestParam(value = "apiKey", required = false) String apiKey,
                              @RequestParam(value = "customerId", required = false) Long customerId,
                              Model model){
        CustomResponse customResponse = new CustomResponse();
        Enterprise enterprise = new Enterprise();
        Customer customer = new Customer();
        String apiKeyToUse = (apiKey != null) ? apiKey :
                             (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")
                             ? authorizationHeader.substring(7) : null);

       customResponse = serviceEnterprise.getEnterprise(null, apiKeyToUse);
        List<Enterprise> lstEnterprise = (List<Enterprise>) customResponse.getLstValue();
        model.addAttribute("lstEnterprise", lstEnterprise);
        if(apiKeyToUse != null && customerId != null){
            customResponse = serviceCustomer.getCustomer(customerId, apiKeyToUse);
        } else if (apiKeyToUse != null){
            customResponse = serviceCustomer.getCustomer(null, apiKeyToUse);
        }
        if(customResponse.getStatusCode() == 200 && customerId != null){
            customer = (Customer) customResponse.getLstValue();
            model.addAttribute("customer", customer);
        } else if(customResponse.getStatusCode() == 200){
            customer.setEnterprise(enterprise);
            model.addAttribute("customer", customer);
        }
        return "crearCustomer";
    }
    @PostMapping("/crearCustomer")
    public String postCreateCustomer(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                                     @RequestParam(value = "apiKey", required = false) String apiKey,
                                     @ModelAttribute Customer modelCustomer){
        CustomResponse customResponse = new CustomResponse();
        Customer customer = new Customer();
        if (modelCustomer.getId() != null) customer.setId(modelCustomer.getId());
        customer.setName(modelCustomer.getName());
        customer.setAddress(modelCustomer.getAddress());
        customer.setEmail(modelCustomer.getEmail());
        customer.setLastName(modelCustomer.getLastName());
        customer.setPhone(modelCustomer.getPhone());
        customer.setEnterprise(modelCustomer.getEnterprise());
        String apiKeyToUse = (apiKey != null) ? apiKey :
                (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")
                        ? authorizationHeader.substring(7) : null);
        if(apiKeyToUse != null && customer.getId() != null){
            customResponse = serviceCustomer.updateCustomer(apiKey, customer);
        } else if(apiKeyToUse != null){
            customResponse = serviceCustomer.createCustomer(apiKey, customer);
        }
        if(customResponse.getStatusCode() == 200){

        }
        return "redirect:/crearCustomer?apiKey=" + apiKeyToUse;
    }

    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                                 @RequestParam(value = "apiKey", required = false) String apiKey,
                                 @RequestParam(value = "customerId", required = false) Long enterpriseId){
        CustomResponse customResponse = new CustomResponse();
        Customer customer = new Customer();
        customer.setId(enterpriseId);
        String apiKeyToUse = (apiKey != null) ? apiKey :
                (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")
                        ? authorizationHeader.substring(7) : null);
        if(apiKeyToUse != null && enterpriseId != null){
            customResponse = serviceCustomer.deleteCustomer(apiKeyToUse, customer);
        }
        if(customResponse.getStatusCode() == 200){

        }
        return "redirect:/customer?apiKey=" + apiKeyToUse;
    }

    @GetMapping("/customer")
    public String getCustomer(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                              @RequestParam(value = "apiKey", required = false) String apiKey,
                              Model model){
        CustomResponse customResponse = new CustomResponse();
        String apiKeyToUse = (apiKey != null) ? apiKey :
                (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")
                        ? authorizationHeader.substring(7) : null);
        if(apiKeyToUse != null){
            customResponse = serviceCustomer.getCustomer(null, apiKeyToUse);
            model.addAttribute("lstCustomer", customResponse.getLstValue());
        }else{
            return "/customer";
        }
        return "/customer";
    }
}
