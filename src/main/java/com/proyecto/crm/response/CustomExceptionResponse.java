package com.proyecto.crm.response;

import org.springframework.lang.Nullable;

import java.util.List;

public class CustomExceptionResponse extends Exception{
    public static CustomResponse getResponseException(int statusCode , String message, @Nullable List<?> lstValue) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setStatusCode(statusCode);
        customResponse.setMessage(message);
        customResponse.setLstValue(lstValue);
        System.out.println(message);
        return customResponse;
    }
}

