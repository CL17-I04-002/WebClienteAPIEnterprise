package com.proyecto.crm.entity;

import lombok.Data;

@Data
public class Customer {
    private Long id;
    private String name;
    private String lastName;
    private Enterprise enterprise;
    private String email;
    private String phone;
    private String address;
}
