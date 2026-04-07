package com.transer.backend.services;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import com.transer.backend.excecption.ResourceNotFoundException;
import com.transer.backend.models.dto.OrdenesRepuestosRequest;
import com.transer.backend.models.entities.OrdenesRepuestos;
import com.transer.backend.models.entities.OrdenesTrabajo;
import com.transer.backend.repositories.OrdenesRepuestosRepository;
import com.transer.backend.repositories.OrdenesTrabajoRepository;

@Service
public class OrdenesRepuestosService {

    private OrdenesRepuestosRepository ordenesRepuestosRepository;
    private OrdenesTrabajoRepository ordenesTrabajoRepository;

    public OrdenesRepuestosService(
            OrdenesRepuestosRepository ordenesRepuestosRepository,
            OrdenesTrabajoRepository ordenesTrabajoRepository) {
        this.ordenesRepuestosRepository = ordenesRepuestosRepository;
        this.ordenesTrabajoRepository = ordenesTrabajoRepository;
    }

    public OrdenesRepuestos createOrdenRepuesto(OrdenesRepuestosRequest request) throws BadRequestException {

        if (request.getOrden_trabajo_id() == null) {
            throw new BadRequestException("orden_trabajo_id es obligatorio");
        }

        if (request.getNombre_repuesto() == null || request.getNombre_repuesto().trim().isEmpty()) {
            throw new BadRequestException("nombre_repuesto es obligatorio");
        }

        if (request.getCantidad() == null || request.getCantidad() <= 0) {
            throw new BadRequestException("cantidad debe ser mayor a 0");
        }

        if (request.getCosto_unitario() == null || request.getCosto_unitario() <= 0) {
            throw new BadRequestException("costo_unitario debe ser mayor a 0");
        }

        OrdenesTrabajo ordenTrabajo = ordenesTrabajoRepository.findById(request.getOrden_trabajo_id())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Orden de trabajo no encontrada: " + request.getOrden_trabajo_id()));

        OrdenesRepuestos ordenRepuesto = new OrdenesRepuestos();
        ordenRepuesto.setOrden_trabajo_id(ordenTrabajo);
        ordenRepuesto.setNombre_repuesto(request.getNombre_repuesto());
        ordenRepuesto.setCantidad(request.getCantidad());
        ordenRepuesto.setCosto_unitario(request.getCosto_unitario());

        return ordenesRepuestosRepository.save(ordenRepuesto);
    }

    public List<OrdenesRepuestos> getAllOrdenesRepuestos() {
        return ordenesRepuestosRepository.findAll();
    }

    public OrdenesRepuestos getOrdenRepuestoById(Long id) {
        return ordenesRepuestosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden de repuesto no encontrada: " + id));
    }
}