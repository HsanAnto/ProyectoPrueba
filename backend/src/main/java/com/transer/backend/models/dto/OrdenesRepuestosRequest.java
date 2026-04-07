package com.transer.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdenesRepuestosRequest {

    private Long orden_trabajo_id;
    private String nombre_repuesto;
    private Integer cantidad;
    private Double costo_unitario;
}