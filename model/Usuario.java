package com.complete.eCommerce.model;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario {
    private Integer id;
    private String nombre;
    private String email;
    private String direccion;
    private String telefono;
    private String tipo; // para los dos niveles de tipo de usuario
    private String password;


}
