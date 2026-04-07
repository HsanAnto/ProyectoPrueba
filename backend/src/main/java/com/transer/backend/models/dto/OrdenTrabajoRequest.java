package com.transer.backend.models.dto;

import com.transer.backend.models.enums.OrdenesTrabajoStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdenTrabajoRequest {
    private Long vehicle_id;
    private Long tipo_ot_id;
    private OrdenesTrabajoStatus status;
    private String descripcion;
    private String created_by;
}
