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
@Table(name = "estado_cintas")
public class EstadoCintaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEstado_cintas")
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Column(name = "nombre_estado_cintas", length = 20)
    private String nombreEstadoCintas;

    @OneToMany(mappedBy = "fkEstadoCintas")
    private Set<InventarioEntity> inventarios = new LinkedHashSet<>();

    @OneToMany(mappedBy = "fkEstadoCintas")
    private Set<MedioEntity> medios = new LinkedHashSet<>();

}