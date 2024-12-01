package com.proyecto.crm.services.interfaces;

import com.proyecto.crm.entity.ServicesApiKey;
import com.proyecto.crm.response.CustomResponse;
import com.proyecto.crm.services.dto.ServiceApiKeyDto;

public interface IServiceApiKey {
    CustomResponse createServiceApiKey(ServicesApiKey servicesApiKey);
    CustomResponse validateApiKey(String apiKey);
}
