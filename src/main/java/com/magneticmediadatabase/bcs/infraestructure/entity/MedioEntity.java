package com.magneticmediadatabase.bcs.infraestructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "medio")
public class MedioEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMedio", nullable = false)
    private Integer id;

    @Size(max = 8, message = "El numero de medio debe tener 8 caracteres")
    @NotBlank(message = "El numero de medio no debe ir en blanco")
    @NotNull
    @Column(name = "codigo_cinta", nullable = false, length = 8)
    private String codigoCinta;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_Estado_cintas")
    private com.magneticmediadatabase.bcs.infraestructure.entity.EstadoCintaEntity fkEstadoCintas;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_Catalogo")
    private com.magneticmediadatabase.bcs.infraestructure.entity.CatalogoEntity fkCatalogo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_Ubicacion")
    private com.magneticmediadatabase.bcs.infraestructure.entity.UbicacionFisicaEntity fkUbicacion;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_Tipo_medio")
    private com.magneticmediadatabase.bcs.infraestructure.entity.TipoMedioEntity fkTipoMedio;

    @OneToMany(mappedBy = "fkMedio")
    private Set<com.magneticmediadatabase.bcs.infraestructure.entity.IronLlegadaEntity> ironLlegadas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "fkMedio")
    private Set<com.magneticmediadatabase.bcs.infraestructure.entity.IronSalidaEntity> ironSalidas = new LinkedHashSet<>();

}