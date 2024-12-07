package com.proyecto.crm.services.clases;

import com.proyecto.crm.entity.ServicesApiKey;
import com.proyecto.crm.response.CustomExceptionResponse;
import com.proyecto.crm.response.CustomResponse;
import com.proyecto.crm.response.ResponseServer;
import com.proyecto.crm.services.dto.ServiceApiKeyDto;
import com.proyecto.crm.services.interfaces.IServiceApiKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Collections;

/**
 * @author : Daniel Larin
 * @version : 1.0
 */
@Service
public class ServiceApiKey implements IServiceApiKey {
    private final ResponseServer responseServer;

    /**
     * Inyected ResponseServer
     * @param responseServer
     */
    @Autowired
    public ServiceApiKey(ResponseServer responseServer) {
        this.responseServer = responseServer;
    }

    /**
     * It usually to consume REST Service, which is used to access Service API Key
     * @param servicesApiKey
     */

    /**
     * HTTP POST request communication service Api Key endpoint
     * @param servicesApiKey
     * @return
     */
    @Override
    public CustomResponse createServiceApiKey(ServicesApiKey servicesApiKey) {
        CustomResponse customResponse = new CustomResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ServicesApiKey> request =new HttpEntity<>(servicesApiKey, headers);
        try{
            ResponseEntity<String> response = responseServer.setParameters("serviceApiKey", HttpMethod.POST, request, String.class);
            if(response.getStatusCode().is2xxSuccessful()){
                customResponse.setStatusCode(200);
                customResponse.setMessage(response.getBody());
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
    public CustomResponse validateApiKey(String apiKey) {
        CustomResponse customResponse = new CustomResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request= new HttpEntity<>(apiKey,headers);
        try{
            ResponseEntity<String> response = responseServer.setParameters("serviceApiKey/validate", HttpMethod.POST, request, String.class);
            if(response.getStatusCode().is2xxSuccessful()){
                customResponse.setStatusCode(200);
                customResponse.setMessage("API Key validada correctamente");
                customResponse.setLstValue(Collections.emptyList());
            } else if(response.getStatusCode() == HttpStatus.NOT_FOUND){
                customResponse.setStatusCode(404);
                customResponse.setMessage("API KEY no encontrada");
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
