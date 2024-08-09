package com.complete.eCommerce.controller;


import com.complete.eCommerce.model.Producto;
import com.complete.eCommerce.service.ProductoService;
import jakarta.persistence.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class); //podria hacerse con  SOUT pero es mejor lleva la trazabilidad con esta clase

    @Autowired //inyecto el contenedor del framework a la instancia de la clase
    private ProductoService productoService; //creo un objeto, un instancia de ProductoService para  obtener los productos.

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
}
