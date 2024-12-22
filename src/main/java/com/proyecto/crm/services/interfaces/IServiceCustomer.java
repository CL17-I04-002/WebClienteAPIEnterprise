package com.proyecto.crm.services.interfaces;

import com.proyecto.crm.entity.Customer;
import com.proyecto.crm.response.CustomResponse;
import jakarta.annotation.Nullable;

public interface IServiceCustomer {
    CustomResponse getCustomer(@Nullable Long id, String apiKey);
    CustomResponse createCustomer(String apiKey, Customer customer);
    CustomResponse updateCustomer(String apiKey, Customer customer);
    CustomResponse deleteCustomer(String apiKey, Customer customer);
}
