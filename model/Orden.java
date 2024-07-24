package com.complete.eCommerce.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@Table(name = "ordenes")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numero;
    private Date fechaCreacion;
    private Date fechaRecibida;

    private double total;
//creo un atributo que me permita identificar que usuarios
//  estan registrados en una orden
    @ManyToOne
    private Usuario usuario;
    //creamos un campo que  detalleOrden
    @OneToOne(mappedBy = "orden")
    private DetalleOrden detalle;

}
