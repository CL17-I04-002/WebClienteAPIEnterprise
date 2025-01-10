package com.proyecto.crm.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerTask {
    private Long id;
    private String description;
    private LocalDate limitDate;
    private String state;
    private Customer customer;
}
