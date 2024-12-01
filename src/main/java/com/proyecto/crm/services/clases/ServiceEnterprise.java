package com.proyecto.crm.services.clases;

import com.proyecto.crm.entity.Enterprise;
import com.proyecto.crm.response.CustomExceptionResponse;
import com.proyecto.crm.response.CustomResponse;
import com.proyecto.crm.response.ResponseServer;
import com.proyecto.crm.services.interfaces.IServiceEnterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@Service
public class ServiceEnterprise implements IServiceEnterprise {
    private final ResponseServer responseServer;

    @Autowired
    public ServiceEnterprise(ResponseServer responseServer) {
        this.responseServer = responseServer;
    }

    @Override
    public CustomResponse getEnterprise(String ApiKey) {
        CustomResponse customResponse = new CustomResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", ApiKey);
        HttpEntity<List<Enterprise>> request =new HttpEntity<>(headers);
        try {
            ResponseEntity<List<Enterprise>> response = responseServer.setParameters("enterprise", HttpMethod.GET, request, Void.class);
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
}
