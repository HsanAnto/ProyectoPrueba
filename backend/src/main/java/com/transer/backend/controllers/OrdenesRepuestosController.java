package com.transer.backend.controllers;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transer.backend.models.dto.OrdenesRepuestosRequest;
import com.transer.backend.models.entities.OrdenesRepuestos;
import com.transer.backend.services.OrdenesRepuestosService;

@RestController
@RequestMapping("/api/ordenes-repuestos")
public class OrdenesRepuestosController {

    private OrdenesRepuestosService ordenesRepuestosService;

    public OrdenesRepuestosController(OrdenesRepuestosService ordenesRepuestosService) {
        this.ordenesRepuestosService = ordenesRepuestosService;
    }

    @GetMapping
    public List<OrdenesRepuestos> getAllOrdenesRepuestos() {
        return ordenesRepuestosService.getAllOrdenesRepuestos();
    }

    @GetMapping("/{id}")
    public OrdenesRepuestos getOrdenRepuestoById(@PathVariable Long id) {
        return ordenesRepuestosService.getOrdenRepuestoById(id);
    }

    @PostMapping
    public OrdenesRepuestos createOrdenRepuesto(@RequestBody OrdenesRepuestosRequest request)
            throws BadRequestException {
        return ordenesRepuestosService.createOrdenRepuesto(request);
    }
}