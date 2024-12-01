package com.proyecto.crm.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomResponse {
    private int statusCode;
    private String message;
    private List<?> lstValue;
}
