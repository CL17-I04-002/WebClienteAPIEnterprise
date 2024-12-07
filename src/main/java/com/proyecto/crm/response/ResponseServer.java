package com.proyecto.crm.response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;

/**
 * @author : Daniel Larin
 * @version : 1.0
 */
@Component
public class ResponseServer {
    @Value("${base.url}")
    private String baseUrl;

    /**
     * Generic method to request the server
     * @param urlComplement
     * @param httpMethod
     * @param requestEntity
     * @param responseType
     * @return
     * @param <T>
     */
    public <T>ResponseEntity<T> setParameters(String urlComplement, HttpMethod httpMethod, @NotNull HttpEntity<?> requestEntity, Object responseType) {
        try{
        urlComplement = baseUrl + urlComplement;
        RestTemplate restTemplate = new RestTemplate();

            if (responseType instanceof ParameterizedTypeReference) {
                return restTemplate.exchange(urlComplement, httpMethod, requestEntity, (ParameterizedTypeReference<T>) responseType);
            } else if (responseType instanceof Class) {
                return restTemplate.exchange(urlComplement, httpMethod, requestEntity, (Class<T>) responseType);
            } else {
                throw new IllegalArgumentException("Response not supported");
            }
        } catch (HttpClientErrorException | HttpServerErrorException e){
            System.out.println("Error en la petición HTTP: " + e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body((T) e.getResponseBodyAsString());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
