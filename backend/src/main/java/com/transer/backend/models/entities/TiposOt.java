package com.transer.backend.models.entities;

import com.transer.backend.models.enums.TiposOtenum;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tipos_ot")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TiposOt {

    @Column
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @Nonnull
    @Enumerated(EnumType.STRING)
    private TiposOtenum nombre;

    @Column
    private Boolean is_active;

}
