package com.magneticmediadatabase.bcs.infraestructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class UsuarioEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuarios", nullable = false)
    private Integer id;

    @NotBlank(message = "El primer nombre no debe estar en blanco")
    @Column(name = "primer_nombre", nullable = false, length = 15)
    private String primerNombre;


    @Column(name = "segundo_nombre", length = 15)
    private String segundoNombre;

    @NotBlank(message = "El primer apellido no debe estar en blanco")
    @Column(name = "primer_apellido", nullable = false, length = 15)
    private String primerApellido;

    @Column(name = "segundo_apellido", nullable = false, length = 15)
    private String segundoApellido;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_rol_usuario")
    private com.magneticmediadatabase.bcs.infraestructure.entity.RolUsuarioEntity fkRolUsuario;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_Estado_Usuario")
    private com.magneticmediadatabase.bcs.infraestructure.entity.EstadoUsuarioEntity fkEstadoUsuario;

    @OneToMany(mappedBy = "fkUsuarios")
    private Set<CatalogoEntity> catalogos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "fkUsuarios")
    private Set<com.magneticmediadatabase.bcs.infraestructure.entity.IronLlegadaEntity> ironLlegadas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "fkUsuarios")
    private Set<com.magneticmediadatabase.bcs.infraestructure.entity.IronSalidaEntity> ironSalidas = new LinkedHashSet<>();
}