package com.proyecto.crm.controller;

import com.proyecto.crm.entity.Customer;
import com.proyecto.crm.entity.CustomerTask;
import com.proyecto.crm.response.CustomResponse;
import com.proyecto.crm.services.interfaces.IServiceCustomer;
import com.proyecto.crm.services.interfaces.IServiceCustomerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class CustomerTaskController {
    private final IServiceCustomerTask serviceCustomerTask;
    private final IServiceCustomer serviceCustomer;

    @Autowired
    public CustomerTaskController(IServiceCustomerTask serviceCustomerTask, IServiceCustomer serviceCustomer) {
        this.serviceCustomerTask = serviceCustomerTask;
        this.serviceCustomer = serviceCustomer;
    }

    @GetMapping("/crearCustomerTask")
    public String getCrearCustomerTask(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                                       @RequestParam(value = "apiKey", required = false) String apiKey,
                                       @RequestParam(value = "customerTaskId", required = false) Long customerTaskId,
                                       Model model){
        CustomerTask customerTask = new CustomerTask();
        CustomResponse customResponse = new CustomResponse();
        String apiKeyToUse = (apiKey != null) ? apiKey :
                (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")
                        ? authorizationHeader.substring(7) : null);
        customResponse = serviceCustomer.getCustomer(null, apiKeyToUse);
        List<Customer> lstCustomer = (List<Customer>) customResponse.getLstValue();
        model.addAttribute("lstCustomer", lstCustomer);
        if(apiKeyToUse != null && customerTaskId != null){
            customResponse = serviceCustomerTask.getCustomerTask(customerTaskId, apiKeyToUse);
        } /*else if(apiKeyToUse != null){
            customResponse = serviceCustomer.getCustomer(null, apiKeyToUse);
        }*/
        if(customResponse.getStatusCode() == 200 && customerTaskId != null){
            customerTask = (CustomerTask) customResponse.getLstValue();
            model.addAttribute("customerTask", customerTask);
        } else if(customResponse.getStatusCode() == 200){
            model.addAttribute("customerTask", customerTask);
        }
        return "/crearCustomerTask";

    }

    @PostMapping("/crearCustomerTask")
    public String postCrearCustomerTask(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                                        @RequestParam(value = "apiKey", required = false) String apiKey,
                                        @ModelAttribute CustomerTask modelCustomerTask){
        CustomerTask customerTask = new CustomerTask();
        CustomResponse customResponse = new CustomResponse();
        customerTask.setDescription(modelCustomerTask.getDescription());
        customerTask.setLimitDate(modelCustomerTask.getLimitDate());
        customerTask.setState(modelCustomerTask.getState());
        customerTask.setCustomer(modelCustomerTask.getCustomer());
        String apiKeyToUse = (apiKey != null) ? apiKey :
                (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")
                        ? authorizationHeader.substring(7) : null);
        if(apiKeyToUse != null && modelCustomerTask.getId() != null){
            customerTask.setId(modelCustomerTask.getId());
            customResponse = serviceCustomerTask.updateCustomerTask(apiKeyToUse, customerTask);
        } else if(apiKeyToUse != null){
            customResponse = serviceCustomerTask.createCustomerTask(apiKey, customerTask);
        }
        if(customResponse.getStatusCode() == 200){

        }
        return "redirect:/crearCustomerTask?apiKey=" + apiKeyToUse;
    }

    @GetMapping("/deleteCustomerTask")
    public String deleteCustomer(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                                 @RequestParam(value = "apiKey", required = false) String apiKey,
                                 @RequestParam(value = "customerTaskId", required = false) Long customerTaskId){
        CustomResponse customResponse = new CustomResponse();
        CustomerTask customerTask = new CustomerTask();
        customerTask.setId(customerTaskId);
        String apiKeyToUse = (apiKey != null) ? apiKey :
                (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")
                        ? authorizationHeader.substring(7) : null);
        if(apiKeyToUse != null && customerTaskId != null){
            customResponse = serviceCustomerTask.deleteCustomTask(apiKeyToUse, customerTask);
        }
        if(customResponse.getStatusCode() == 200){

        }
        return "redirect:/customerTask?apiKey=" + apiKeyToUse;
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
