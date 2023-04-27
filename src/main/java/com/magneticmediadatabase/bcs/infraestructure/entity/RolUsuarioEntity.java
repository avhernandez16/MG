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
@Table(name = "rol_usuario")
public class RolUsuarioEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrol_usuario")
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Column(name = "nombre_rol_usuario", length = 20)
    private String nombreRolUsuario;

    @OneToMany(mappedBy = "fkRolUsuario")
    private Set<UsuarioEntity> usuarios = new LinkedHashSet<>();

}