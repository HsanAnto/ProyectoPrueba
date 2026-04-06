package com.transer.backend.services;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import com.transer.backend.excecption.ResourceNotFoundException;
import com.transer.backend.models.OrdenesTrabajo;
import com.transer.backend.models.OrdenesTrabajoStatus;
import com.transer.backend.models.Vehiculo;
import com.transer.backend.repositories.OrdenesTrabajoRepository;

@Service
public class OrdenesTrabajoService {

    private OrdenesTrabajoRepository ordenestrabajoRepository;

    public OrdenesTrabajoService(OrdenesTrabajoRepository ordenestrabajoRepository) {
        this.ordenestrabajoRepository = ordenestrabajoRepository;
    }

    public OrdenesTrabajo createOrder(OrdenesTrabajo ordenTrabajo) throws BadRequestException {
        Vehiculo vehiculo = ordenTrabajo.getVehicle_id();
        Boolean is_active = vehiculo.is_active();
        if (!is_active) {
            throw new BadRequestException("Vehicle must be active ");
        }

        return ordenestrabajoRepository.save(ordenTrabajo);
    }

    public List<OrdenesTrabajo> getAllOrdenesTrabajo() {
        return ordenestrabajoRepository.findAll();
    }

    public OrdenesTrabajo getOrdenTrabajo(Long id) {
        return ordenestrabajoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden con id no encontrada: " + id));
    }

    public OrdenesTrabajo updateStatus(Long id, OrdenesTrabajoStatus status) {
        OrdenesTrabajo orden = ordenestrabajoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden con id no encontrada: " + id));
        orden.setStatus(status);
        ;
        return ordenestrabajoRepository.save(orden);
    }

}
