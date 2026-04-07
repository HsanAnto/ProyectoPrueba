package com.transer.backend.controllers;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.transer.backend.models.entities.TiposOt;
import com.transer.backend.services.TiposOtService;

@RestController
@RequestMapping("/api/tipos-ot")
public class TiposOtController {

    private TiposOtService tiposOtService;

    public TiposOtController(TiposOtService tiposOtService) {
        this.tiposOtService = tiposOtService;
    }

    @GetMapping
    public List<TiposOt> getAllTiposOt() {
        return tiposOtService.getAllTiposOt();
    }

    @GetMapping("/{id}")
    public TiposOt getTipoOtById(@PathVariable Long id) {
        return tiposOtService.getTipoOtById(id);
    }

    @PostMapping
    public TiposOt createTipoOt(@RequestBody TiposOt tipoOt) throws BadRequestException {
        return tiposOtService.createTipoOt(tipoOt);
    }

    @PatchMapping("/{id}/active")
    public TiposOt updateTipoOtActive(@PathVariable Long id, @RequestParam boolean isActive) {
        return tiposOtService.updateActiveStatus(id, isActive);
    }
}
