package com.transer.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.transer.backend.excecption.BadRequestException;
import com.transer.backend.models.entities.Vehiculo;
import com.transer.backend.services.VehiculoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculosController {
    private VehiculoService vehiculoService;

    public VehiculosController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @GetMapping
    public List<Vehiculo> getVehiculos() {
        return vehiculoService.getAllvehicles();
    }

    @GetMapping("/{id}")
    public Vehiculo getVehiculoById(@PathVariable Long id) {
        return vehiculoService.getVehiculoById(id);
    }

    @PostMapping
    public Vehiculo createVehiculo(@RequestBody Vehiculo vehiculo) throws BadRequestException {
        return vehiculoService.createVehiculo(vehiculo);
    }

    @PatchMapping("/{id}/active")
    public Vehiculo updateVehiculoActive(@PathVariable Long id, @RequestParam boolean isActive) {
        return vehiculoService.updateActiveStatus(id, isActive);
    }

}
