package com.transer.backend.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "vehiculos")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Vehiculo {

    @Column
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NonNull
    private String placa;

    @Column
    @NonNull
    private String marca;

    @Column
    private String modelo;

    @Column
    private Integer anio;

    @Column
    private boolean is_active;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created_at;

}
