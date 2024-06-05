package com.complete.eCommerce.model;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Producto {
private Integer id;
private String nombre;
private String descripcion;
private String imagen;
private double precio;
private Integer cantidad;

}
