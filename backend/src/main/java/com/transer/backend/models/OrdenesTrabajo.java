package com.transer.backend.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "ordenes_trabajo")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrdenesTrabajo {

    @Column
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehiculo vehicle_id;

    @ManyToOne
    @JoinColumn(name = "tipo_ot_id")
    private TiposOt tipo_ot_id;

    @Column
    @Enumerated(EnumType.STRING)
    private OrdenesTrabajoStatus status;

    @Column
    private String descripcion;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created_at;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated_at;

    @Column
    private String created_by;

}
