package com.proyecto.crm.services.interfaces;

import com.proyecto.crm.entity.CustomerTask;
import com.proyecto.crm.response.CustomResponse;
import jakarta.annotation.Nullable;

public interface IServiceCustomerTask {
    CustomResponse getCustomerTask(@Nullable Long id, String apiKey);
    CustomResponse createCustomerTask(String apiKey, CustomerTask customerTask);
    CustomResponse updateCustomerTask(String apiKey, CustomerTask customerTask);
    CustomResponse deleteCustomTask(String apiKey, CustomerTask customerTask);
}
