package com.transer.backend.services;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import com.transer.backend.excecption.ResourceNotFoundException;
import com.transer.backend.models.entities.TiposOt;
import com.transer.backend.repositories.TiposOtRepository;

@Service
public class TiposOtService {

    private TiposOtRepository tiposOtRepository;

    public TiposOtService(TiposOtRepository tiposOtRepository) {
        this.tiposOtRepository = tiposOtRepository;
    }

    public TiposOt createTipoOt(TiposOt tipoOt) throws BadRequestException {
        if (tipoOt.getNombre() == null) {
            throw new BadRequestException("El tipo de OT debe tener nombre");
        }

        return tiposOtRepository.save(tipoOt);
    }

    public List<TiposOt> getAllTiposOt() {
        return tiposOtRepository.findAll();
    }

    public TiposOt getTipoOtById(Long id) {
        return tiposOtRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo OT con id no encontrado: " + id));
    }

    public TiposOt updateActiveStatus(Long id, boolean isActive) {
        TiposOt tipoOt = tiposOtRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo OT con id no encontrado: " + id));

        tipoOt.setIs_active(isActive);
        return tiposOtRepository.save(tipoOt);
    }
}