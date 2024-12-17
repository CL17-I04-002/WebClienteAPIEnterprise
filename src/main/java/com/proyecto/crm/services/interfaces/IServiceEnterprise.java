package com.proyecto.crm.services.interfaces;

import com.proyecto.crm.entity.Enterprise;
import com.proyecto.crm.response.CustomResponse;
import jakarta.annotation.Nullable;

public interface IServiceEnterprise {
    CustomResponse getEnterprise(@Nullable Long id, String apiKey);
    CustomResponse createEnterprise(String apiKey, Enterprise enterprise);
    CustomResponse updateEnterprise(String apiKey, Enterprise enterprise);
    CustomResponse deleteEnterprise(String apiKey, Enterprise enterprise);
}
