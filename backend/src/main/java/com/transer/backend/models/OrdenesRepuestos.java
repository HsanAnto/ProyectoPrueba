package com.transer.backend.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orden_repuestos")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class OrdenesRepuestos {

    @Column
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @Nonnull
    @JoinColumn(name = "orden_trabajo_id")
    private OrdenesTrabajo orden_trabajo_id;

    @Column
    @Nonnull
    private String nombre_repuesto;

    @Column
    @Nonnull
    private Integer cantidad;

    @Column
    @Nonnull
    private Double costo_unitario;

}
