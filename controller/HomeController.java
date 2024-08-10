package com.complete.eCommerce.controller;


import com.complete.eCommerce.model.DetalleOrden;
import com.complete.eCommerce.model.Orden;
import com.complete.eCommerce.model.Producto;
import com.complete.eCommerce.service.ProductoService;
import jakarta.persistence.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class); //podria hacerse con  SOUT pero es mejor lleva la trazabilidad con esta clase

    @Autowired //inyecto el contenedor del framework a la instancia de la clase
    private ProductoService productoService; //creo un objeto, un instancia de ProductoService para  obtener los productos.
//clase24 creamos 2 variables

    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

    //almaceno los datos de la orden
    Orden orden = new Orden();


    @GetMapping("")// de esta manera indica que responde a la raiz
    public String home(Model model){
        model.addAttribute("productos", productoService.findAll()); //recibe primero "productos" que seria la variable del home o del show, que va a llevar hacia la vista
        // como segundo parametro  usamos productoService  que con su metodo findall va a cargar  el primer parametro productos con los productos

        return "usuario/home";
    }

    @GetMapping("productohome/{id}")
    public String productoHomre(@PathVariable Integer id, Model model){ //se pone pathvariable para que pueda ser recibido desde la variable que viene
        log.info("Id producto enviado como parametro {}", id);
        Producto producto = new Producto();
        Optional<Producto> productoOptional = productoService.get(id);
        producto = productoOptional.get();
        model.addAttribute("producto", producto);

        return "usuario/productohome";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model){
        //primero declaro las variables que voy a necesitar para la logica
        DetalleOrden detalleOrden = new DetalleOrden();  //creo la variable detalleOrden
        Producto producto = new Producto();// creo objeto tipo Producto
        double sumaTotal = 0;// creo una variable double que almacena la suma total , inicializada en 0

        Optional<Producto> optionalProducto = productoService.get(id); // con esto obtenemos los productos por su id
        log.info("Producto añadido: {} ", optionalProducto.get() );
        log.info("Cantidad: {}", cantidad); //sirve para revisar la cantidad por consola

    //clase25
        producto = optionalProducto.get(); //cargo la variable producto con lo que tenemos en optionalproducto desde la base de datos

        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio()* cantidad); //precio total por unidad
        detalleOrden.setProducto(producto);

        detalles.add(detalleOrden); //añado cada detalle orden anterior a detalles

        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles); //previo a crear el argumento Model en la firma del  metodo paso los datos para luego imprimir la vista
        model.addAttribute("orden", orden);
        return "usuario/carrito";
    }





}
