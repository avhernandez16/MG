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
@Table(name = "catalogo")
public class CatalogoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCatalogo")
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Column(name = "nombre_catalogo", length = 20)
    private String nombreCatalogo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_Usuarios")
    private UsuarioEntity fkUsuarios;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_Ciclo_catalogo")
    private com.magneticmediadatabase.bcs.infraestructure.entity.CicloCatalogoEntity fkCicloCatalogo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_Consola")
    private com.magneticmediadatabase.bcs.infraestructure.entity.ConsolaEntity fkConsola;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_Programa")
    private com.magneticmediadatabase.bcs.infraestructure.entity.ProgramaEntity fkPrograma;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_Tecnologias")
    private com.magneticmediadatabase.bcs.infraestructure.entity.TecnologiaEntity fkTecnologias;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_IP")
    private com.magneticmediadatabase.bcs.infraestructure.entity.IpEntity fkIp;

    @OneToMany(mappedBy = "fkCatalogo")
    private Set<MedioEntity> medios = new LinkedHashSet<>();

}