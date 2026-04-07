package com.transer.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transer.backend.models.dto.OrdenTrabajoRequest;
import com.transer.backend.models.entities.OrdenesTrabajo;
import com.transer.backend.models.enums.OrdenesTrabajoStatus;
import com.transer.backend.services.OrdenesTrabajoService;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenesTrabajoController {
    private OrdenesTrabajoService ordenesTrabajoService;

    public OrdenesTrabajoController(OrdenesTrabajoService ordenesTrabajoService) {
        this.ordenesTrabajoService = ordenesTrabajoService;
    }

    @GetMapping
    public List<OrdenesTrabajo> getAllOrders() {
        return ordenesTrabajoService.getAllOrdenesTrabajo();
    }

    @GetMapping("/{id}")
    public OrdenesTrabajo getOrderById(@PathVariable Long id) {
        return ordenesTrabajoService.getOrdenTrabajo(id);
    }

    @PostMapping
    public OrdenesTrabajo postMethodName(@RequestBody OrdenTrabajoRequest request) throws BadRequestException {
        return ordenesTrabajoService.createOrder(request);
    }

    @PatchMapping("/{id}/status")
    public OrdenesTrabajo patchOrder(@PathVariable Long id, @RequestParam OrdenesTrabajoStatus status) {
        return ordenesTrabajoService.updateStatus(id, status);
    }

}
