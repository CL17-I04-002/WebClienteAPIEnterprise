package com.proyecto.crm.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServiceApiKeyDto {
    private String serviceName;
    private String usuarioCreacion;
    private String usuarioModificacion;
    private Boolean isAdmin;
}