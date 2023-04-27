package com.magneticmediadatabase.bcs.infraestructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tipo_medio")
public class TipoMedioEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipo_medio")
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Column(name = "nombre_tipo_medio", length = 20)
    private String nombreTipoMedio;

    @OneToMany(mappedBy = "fkTipoMedio")
    private Set<MedioEntity> medios = new LinkedHashSet<>();

}