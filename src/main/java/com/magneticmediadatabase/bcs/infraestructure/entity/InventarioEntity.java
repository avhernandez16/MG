package com.magneticmediadatabase.bcs.infraestructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "inventario")
public class InventarioEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInventario")
    private Integer id;

    @NotNull
    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @NotNull
    @Column(name = "cantidad")
    private Integer cantidad;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_Proveedor")
    private ProveedoreEntity fkProveedor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_Ubicacion")
    private UbicacionFisicaEntity fkUbicacion;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_Tecnologias")
    private TecnologiaEntity fkTecnologias;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_Estado_cintas")
    private EstadoCintaEntity fkEstadoCintas;

}