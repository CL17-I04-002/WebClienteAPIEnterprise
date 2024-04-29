package com.proyecto.crm.services.interfaces;

import com.proyecto.crm.entity.ServicesApiKey;
import com.proyecto.crm.response.CustomResponse;

public interface IServiceApiKey {
    CustomResponse createServiceApiKey(ServicesApiKey servicesApiKey);
}
