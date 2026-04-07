package com.transer.backend.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.transer.backend.excecption.BadRequestException;
import com.transer.backend.excecption.ResourceNotFoundException;
import com.transer.backend.models.entities.Vehiculo;
import com.transer.backend.repositories.VehiculosRepository;

@Service
public class VehiculoService {

    private VehiculosRepository vehiculosRepository;

    public VehiculoService(VehiculosRepository vehiculosRepository) {
        this.vehiculosRepository = vehiculosRepository;
    }

    public List<Vehiculo> getAllvehicles() {
        return vehiculosRepository.findAll();
    }

    public Vehiculo createVehiculo(Vehiculo vehiculo) throws BadRequestException {
        if (vehiculo.getPlaca() == null || vehiculo.getPlaca().trim().isEmpty()) {
            throw new BadRequestException("El vehículo debe tener placa");
        }

        if (vehiculo.getMarca() == null || vehiculo.getMarca().trim().isEmpty()) {
            throw new BadRequestException("El vehículo debe tener marca");
        }

        vehiculo.setCreated_at(LocalDateTime.now());

        return vehiculosRepository.save(vehiculo);
    }

    public Vehiculo getVehiculoById(Long id) {
        return vehiculosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehículo con id no encontrado: " + id));
    }

    public Vehiculo updateActiveStatus(Long id, boolean isActive) {
        Vehiculo vehiculo = vehiculosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehículo con id no encontrado: " + id));

        vehiculo.setIs_active(isActive);
        return vehiculosRepository.save(vehiculo);
    }

}
