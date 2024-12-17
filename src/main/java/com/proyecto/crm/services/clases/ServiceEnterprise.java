package com.proyecto.crm.services.clases;

import com.proyecto.crm.entity.Enterprise;
import com.proyecto.crm.response.CustomExceptionResponse;
import com.proyecto.crm.response.CustomResponse;
import com.proyecto.crm.response.ResponseServer;
import com.proyecto.crm.services.interfaces.IServiceEnterprise;
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
public class ServiceEnterprise implements IServiceEnterprise {
    private final ResponseServer responseServer;

    @Autowired
    public ServiceEnterprise(ResponseServer responseServer) {
        this.responseServer = responseServer;
    }

    @Override
    public CustomResponse getEnterprise(@Nullable Long id, String apiKey) {
        CustomResponse customResponse = new CustomResponse();
        String url = "";
        if(id != null) url = "enterprise?id=" + id;
        else url = "enterprise";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", apiKey);
        HttpEntity<List<Enterprise>> request =new HttpEntity<>(headers);
        try {
            ResponseEntity<Object> response = responseServer.setParameters(url, HttpMethod.GET, request, (id != null) ? Enterprise.class : new ParameterizedTypeReference<List<Enterprise>>() {});
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
    public CustomResponse createEnterprise(String apiKey, Enterprise enterprise) {
        CustomResponse customResponse = new CustomResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", apiKey);
        HttpEntity<Enterprise> request = new HttpEntity<>(enterprise ,headers);
        try{
            ResponseEntity<Enterprise> response = responseServer.setParameters("enterprise", HttpMethod.POST, request, String.class);
            if(response.getStatusCode().is2xxSuccessful()){
                customResponse.setMessage("Enterprise was registered");
                customResponse.setStatusCode(200);
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
    public CustomResponse updateEnterprise(String apiKey, Enterprise enterprise) {
        CustomResponse customResponse = new CustomResponse();
        String url = "enterprise/" + enterprise.getId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", apiKey);
        HttpEntity<Enterprise> request = new HttpEntity<>(enterprise ,headers);
        if(enterprise.getId() != null || enterprise.getId() != 0){
            try{
                ResponseEntity<String> response = responseServer.setParameters(url, HttpMethod.PUT, request, String.class);
                if(response.getStatusCode().is2xxSuccessful()){
                    customResponse.setStatusCode(200);
                    customResponse.setMessage(response.getBody());
                }
            } catch (HttpClientErrorException e) {
                return CustomExceptionResponse.getResponseException(500 ,"Client error communicating with API: " + e.getMessage(), null);
            } catch (HttpServerErrorException e) {
                return CustomExceptionResponse.getResponseException(500 ,"Server error when communicating with the API: " + e.getMessage(), null);
            } catch (Exception e){
                return CustomExceptionResponse.getResponseException(500 ,"An unexpected error occurred while obtaining the API Key", null);
            }
        }
        return customResponse;
    }

    @Override
    public CustomResponse deleteEnterprise(String apiKey, Enterprise enterprise) {
        CustomResponse customResponse = new CustomResponse();
        String url = "enterprise/" + enterprise.getId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", apiKey);
        HttpEntity<Enterprise> request = new HttpEntity<>(enterprise, headers);
        if(enterprise.getId() != null || enterprise.getId() != 0){
            try{
                ResponseEntity<String> response = responseServer.setParameters(url, HttpMethod.DELETE, request, String.class);
                if(response.getStatusCode().is2xxSuccessful()){
                    customResponse.setStatusCode(200);
                    customResponse.setMessage(response.getBody());
                }
            } catch (HttpClientErrorException e) {
                return CustomExceptionResponse.getResponseException(500 ,"Client error communicating with API: " + e.getMessage(), null);
            } catch (HttpServerErrorException e) {
                return CustomExceptionResponse.getResponseException(500 ,"Server error when communicating with the API: " + e.getMessage(), null);
            } catch (Exception e){
                return CustomExceptionResponse.getResponseException(500 ,"An unexpected error occurred while obtaining the API Key", null);
            }
        }
        return customResponse;
    }
}