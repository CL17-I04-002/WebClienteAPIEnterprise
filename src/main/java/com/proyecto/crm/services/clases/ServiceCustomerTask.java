package com.proyecto.crm.services.clases;

import com.proyecto.crm.entity.CustomerTask;
import com.proyecto.crm.response.CustomExceptionResponse;
import com.proyecto.crm.response.CustomResponse;
import com.proyecto.crm.response.ResponseServer;
import com.proyecto.crm.services.interfaces.IServiceCustomerTask;
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
public class ServiceCustomerTask implements IServiceCustomerTask {
    private final ResponseServer responseServer;

    @Autowired
    public ServiceCustomerTask(ResponseServer responseServer) {
        this.responseServer = responseServer;
    }

    @Override
    public CustomResponse getCustomerTask(@Nullable Long id, String apiKey) {
        CustomResponse customResponse = new CustomResponse();
        HttpHeaders headers = new HttpHeaders();
        String url = "";
        if (id != null) url = "customerTask?id=" + id;
        else url = "customerTask";
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", apiKey);
        HttpEntity<String> request = new HttpEntity<>(apiKey, headers);
        try{
            ResponseEntity<Object> response = responseServer.setParameters(url, HttpMethod.GET, request, (id != null) ? CustomerTask.class : new ParameterizedTypeReference<List<CustomerTask>>() {});
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
    public CustomResponse createCustomerTask(String apiKey, CustomerTask customerTask) {
        CustomResponse customResponse = new CustomResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", apiKey);
        HttpEntity<CustomerTask> request = new HttpEntity<>(customerTask, headers);
        try{
            ResponseEntity<CustomerTask> response = responseServer.setParameters("customerTask", HttpMethod.POST, request, String.class);
            if(response.getStatusCode().is2xxSuccessful()){
                customResponse.setStatusCode(200);
                customResponse.setMessage("CustomerTask was created");
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
    public CustomResponse updateCustomerTask(String apiKey, CustomerTask customerTask) {
        CustomResponse customResponse = new CustomResponse();
        HttpHeaders headers = new HttpHeaders();
        String url = "customerTask/" + customerTask.getId().toString();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", apiKey);
        HttpEntity<CustomerTask> request = new HttpEntity<>(customerTask, headers);
        try{
            ResponseEntity<CustomerTask> response = responseServer.setParameters(url, HttpMethod.PUT, request, String.class);
            if(response.getStatusCode().is2xxSuccessful()){
                customResponse.setStatusCode(200);
                customResponse.setMessage("customerTask was updated");
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
    public CustomResponse deleteCustomTask(String apiKey, CustomerTask customerTask) {
        CustomResponse customResponse = new CustomResponse();
        HttpHeaders headers = new HttpHeaders();
        String url = "customerTask/" + customerTask.getId().toString();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", apiKey);
        HttpEntity<CustomerTask> request = new HttpEntity<>(customerTask, headers);
        try{
            ResponseEntity<CustomerTask> response = responseServer.setParameters(url, HttpMethod.DELETE, request, String.class);
            if(response.getStatusCode().is2xxSuccessful()){
                customResponse.setStatusCode(200);
                customResponse.setMessage("customerTask was deleted");
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
