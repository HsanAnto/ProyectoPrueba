package com.transer.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.transer.backend.models.Vehiculo;
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

}
