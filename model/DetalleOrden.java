package com.complete.eCommerce.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString // siempre el ToString para que nos devuelva el objeto completo
@Table(name =  "detalles")
public class DetalleOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private double cantidad;
    private double precio;
    private double total;

    //ahora creamos las relaciones correspondientes con JPA entre las clases Orden y al Producto
    @OneToOne
    private  Orden orden;

    @ManyToOne
    private Producto producto;

}
