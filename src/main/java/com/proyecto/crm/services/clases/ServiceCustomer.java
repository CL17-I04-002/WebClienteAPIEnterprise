package com.proyecto.crm.services.clases;

import com.proyecto.crm.entity.Customer;
import com.proyecto.crm.response.CustomExceptionResponse;
import com.proyecto.crm.response.CustomResponse;
import com.proyecto.crm.response.ResponseServer;
import com.proyecto.crm.services.interfaces.IServiceCustomer;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Collections;
import java.util.List;

@Service
public class ServiceCustomer implements IServiceCustomer {
    private final ResponseServer responseServer;

    @Autowired
    public ServiceCustomer(ResponseServer responseServer) {
        this.responseServer = responseServer;
    }


    @Override
    public CustomResponse getCustomer(@Nullable Long id, String apiKey) {
        CustomResponse customResponse = new CustomResponse();
        HttpHeaders headers = new HttpHeaders();
        String url = "";
        if (id != null) url = "customer?id=" + id;
        else url = "customer";
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", apiKey);
        HttpEntity<String> request = new HttpEntity<>(apiKey, headers);
        try{
            ResponseEntity<Object> response = responseServer.setParameters(url, HttpMethod.GET, request, (id != null) ? Customer.class : new ParameterizedTypeReference<List<Customer>>() {});
            if(response.getStatusCode().is2xxSuccessful()){
                customResponse.setStatusCode(200);
                customResponse.setMessage("OK");
                customResponse.setLstValue(response.getBody());
            }
            return customResponse;
        } catch (HttpClientErrorException e) {
            return CustomExceptionResponse.getResponseException(500 ,"Client error communicating with API: " + e.getMessage(), null);
        } catch (HttpServerErrorException e) {
            return CustomExceptionResponse.getResponseException(500 ,"Server error when communicating with the API: " + e.getMessage(), null);
        } catch (Exception e){
            return CustomExceptionResponse.getResponseException(500 ,"An unexpected error occurred while obtaining the API Key", null);
        }
    }

    @Override
    public CustomResponse createCustomer(String apiKey, Customer customer) {
        CustomResponse customResponse = new CustomResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", apiKey);
        HttpEntity<String> request = new HttpEntity<>(apiKey, headers);
        try{
            ResponseEntity<Customer> response = responseServer.setParameters("customer", HttpMethod.POST, request, String.class);
            if(response.getStatusCode().is2xxSuccessful()){
                customResponse.setStatusCode(200);
                customResponse.setMessage("Customer was created");
                customResponse.setLstValue(Collections.emptyList());
            }
            return customResponse;
        } catch (HttpClientErrorException e) {
            return CustomExceptionResponse.getResponseException(500 ,"Client error communicating with API: " + e.getMessage(), null);
        } catch (HttpServerErrorException e) {
            return CustomExceptionResponse.getResponseException(500 ,"Server error when communicating with the API: " + e.getMessage(), null);
        } catch (Exception e){
            return CustomExceptionResponse.getResponseException(500 ,"An unexpected error occurred while obtaining the API Key", null);
        }
    }

    @Override
    public CustomResponse updateCustomer(String apiKey, Customer customer) {
        CustomResponse customResponse = new CustomResponse();
        String url = "customer/" + customer.getId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", apiKey);
        HttpEntity<String> request = new HttpEntity<>(apiKey, headers);
        try{
            ResponseEntity<Customer> response = responseServer.setParameters(url, HttpMethod.PUT, request, String.class);
            if(response.getStatusCode().is2xxSuccessful()){
                customResponse.setStatusCode(200);
                customResponse.setMessage("Customer was updated");
                customResponse.setLstValue(Collections.emptyList());
            }
            return customResponse;
        } catch (HttpClientErrorException e) {
            return CustomExceptionResponse.getResponseException(500 ,"Client error communicating with API: " + e.getMessage(), null);
        } catch (HttpServerErrorException e) {
            return CustomExceptionResponse.getResponseException(500 ,"Server error when communicating with the API: " + e.getMessage(), null);
        } catch (Exception e){
            return CustomExceptionResponse.getResponseException(500 ,"An unexpected error occurred while obtaining the API Key", null);
        }
    }

    @Override
    public CustomResponse deleteCustomer(String apiKey, Customer customer) {
        CustomResponse customResponse = new CustomResponse();
        String url = "customer/" + customer.getId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", apiKey);
        HttpEntity<String> request = new HttpEntity<>(apiKey, headers);
        try{
            ResponseEntity<Customer> response = responseServer.setParameters(url, HttpMethod.DELETE, request, String.class);
            if(response.getStatusCode().is2xxSuccessful()){
                customResponse.setStatusCode(200);
                customResponse.setMessage("Customer was deleted");
                customResponse.setLstValue(Collections.emptyList());
            }
            return customResponse;
        } catch (HttpClientErrorException e) {
            return CustomExceptionResponse.getResponseException(500 ,"Client error communicating with API: " + e.getMessage(), null);
        } catch (HttpServerErrorException e) {
            return CustomExceptionResponse.getResponseException(500 ,"Server error when communicating with the API: " + e.getMessage(), null);
        } catch (Exception e){
            return CustomExceptionResponse.getResponseException(500 ,"An unexpected error occurred while obtaining the API Key", null);
        }
    }
}
