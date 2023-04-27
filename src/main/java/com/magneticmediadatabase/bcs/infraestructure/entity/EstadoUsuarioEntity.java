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
@Table(name = "estado_usuario")
public class EstadoUsuarioEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEstado_Usuario")
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Column(name = "Estado", length = 20)
    private String estado;

    @OneToMany(mappedBy = "fkEstadoUsuario")
    private Set<UsuarioEntity> usuarios = new LinkedHashSet<>();

}