package com.proyecto.crm.controller;

import com.proyecto.crm.response.CustomResponse;
import com.proyecto.crm.services.interfaces.IServiceCustomerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class CustomerTaskController {
    private final IServiceCustomerTask serviceCustomerTask;

    @Autowired
    public CustomerTaskController(IServiceCustomerTask serviceCustomerTask) {
        this.serviceCustomerTask = serviceCustomerTask;
    }

    @GetMapping("/customerTask")
    public String getCustomTask(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                                @RequestParam(value = "apiKey",required = false) String apiKey,
                                Model model){
        CustomResponse customResponse = new CustomResponse();
        String apiKeyToUse = (apiKey != null) ? apiKey :
                            (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")
                                    ? authorizationHeader.substring(7) : null);
        if(apiKeyToUse != null){
             customResponse = serviceCustomerTask.getCustomerTask(null, apiKeyToUse);
             model.addAttribute("lstCustomResponse", customResponse.getLstValue());
        }
        return "/customerTask";
    }
}
