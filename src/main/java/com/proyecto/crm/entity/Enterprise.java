package com.proyecto.crm.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Enterprise implements Serializable {
    private Long id;
    private String name;
    private String contactEmail;
    private String contactPhone;
    private String address;
}
