package com.complete.eCommerce.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "productos")
public class Producto {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private String nombre;
private String descripcion;
private String imagen;
private double precio;
private Integer cantidad;

@ManyToOne //con esto vamos a crear internamente un campo en la tabla
//productos para enviar el  id de usuario
private Usuario usuario;

}
