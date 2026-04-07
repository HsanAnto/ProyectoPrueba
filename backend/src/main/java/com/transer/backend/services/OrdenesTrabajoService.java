package com.transer.backend.services;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import com.transer.backend.excecption.ResourceNotFoundException;
import com.transer.backend.models.dto.OrdenTrabajoRequest;
import com.transer.backend.models.entities.OrdenesTrabajo;
import com.transer.backend.models.entities.TiposOt;
import com.transer.backend.models.entities.Vehiculo;
import com.transer.backend.models.enums.OrdenesTrabajoStatus;
import com.transer.backend.repositories.OrdenesTrabajoRepository;
import com.transer.backend.repositories.TiposOtRepository;
import com.transer.backend.repositories.VehiculosRepository;

@Service
public class OrdenesTrabajoService {

    private OrdenesTrabajoRepository ordenesTrabajoRepository;
    private VehiculosRepository vehiculoRepository;
    private TiposOtRepository tiposOtRepository;

    public OrdenesTrabajoService(
            OrdenesTrabajoRepository ordenesTrabajoRepository,
            VehiculosRepository vehiculoRepository,
            TiposOtRepository tiposOtRepository) {
        this.ordenesTrabajoRepository = ordenesTrabajoRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.tiposOtRepository = tiposOtRepository;
    }

    public OrdenesTrabajo createOrder(OrdenTrabajoRequest request) throws BadRequestException {
        Vehiculo vehiculo = vehiculoRepository.findById(request.getVehicle_id())
                .orElseThrow(() -> new ResourceNotFoundException("Vehículo no encontrado: " + request.getVehicle_id()));

        TiposOt tipoOt = tiposOtRepository.findById(request.getTipo_ot_id())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo OT no encontrado: " + request.getTipo_ot_id()));

        if (!vehiculo.getIs_active()) {
            throw new BadRequestException("Vehicle must be active");
        }

        if (request.getStatus() != OrdenesTrabajoStatus.ABIERTA) {
            throw new BadRequestException("Oden debe inicializarce como ABIERTA");
        }

        OrdenesTrabajo orden = new OrdenesTrabajo();
        orden.setVehicle_id(vehiculo);
        orden.setTipo_ot_id(tipoOt);
        orden.setStatus(request.getStatus());
        orden.setDescripcion(request.getDescripcion());
        orden.setCreated_by(request.getCreated_by());
        orden.setCreated_at(LocalDateTime.now());

        return ordenesTrabajoRepository.save(orden);
    }

    public List<OrdenesTrabajo> getAllOrdenesTrabajo() {
        return ordenesTrabajoRepository.findAll();
    }

    public OrdenesTrabajo getOrdenTrabajo(Long id) {
        return ordenesTrabajoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden con id no encontrada: " + id));
    }

    public OrdenesTrabajo updateStatus(Long id, OrdenesTrabajoStatus status) {
        OrdenesTrabajo orden = ordenesTrabajoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden con id no encontrada: " + id));
        orden.setStatus(status);
        return ordenesTrabajoRepository.save(orden);
    }
}