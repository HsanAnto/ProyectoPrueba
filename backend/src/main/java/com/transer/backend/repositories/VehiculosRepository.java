package com.transer.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transer.backend.models.entities.Vehiculo;

public interface VehiculosRepository extends JpaRepository<Vehiculo, Long> {

}
