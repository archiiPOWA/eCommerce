package com.complete.eCommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String username;
    private String email;
    private String direccion;
    private String telefono;
    private String tipo; // para los dos niveles de tipo de usuario
    private String password;

    @OneToMany(mappedBy = "usuario")
    private List<Producto> productos;

    //ahora creamos una relacion entre la clase usuario y ordenes
    //quiero obtener una lista de ordenes para un usuario, un usuario puede tener
    //muchas ordenes
    @OneToMany(mappedBy = "usuario")
    private List<Orden> ordenes;


    public Usuario(int i, String cesar, String mail, String s, String s1, String admin, String password123, String string) {
    }
}
