package com.proyecto.crm.response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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
        urlComplement = baseUrl + urlComplement;
        RestTemplate restTemplate = new RestTemplate();
        if(responseType instanceof ParameterizedTypeReference){
            return restTemplate.exchange(urlComplement, httpMethod, requestEntity, (ParameterizedTypeReference<T>) responseType);
        } else if(responseType instanceof Class){
            return restTemplate.exchange(urlComplement, httpMethod, requestEntity, (Class<T>) responseType);
        } else {
            throw new IllegalArgumentException("Response not supported");
        }
    }
}
