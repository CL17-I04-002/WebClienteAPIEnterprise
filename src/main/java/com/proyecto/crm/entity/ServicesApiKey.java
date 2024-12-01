package com.proyecto.crm.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServicesApiKey implements Serializable {
    private Integer id;
    private String service;
    private String apiKey;
}
