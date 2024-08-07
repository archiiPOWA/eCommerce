package com.complete.eCommerce.controller;


import com.complete.eCommerce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired //inyecto el contenedor del framework a la instancia de la clase
    private ProductoService productoService; //creo un objeto, un instancia de ProductoService para  obtener los productos.

    @GetMapping("")// de esta manera indica que responde a la raiz
    public String home(Model model){
        model.addAttribute("productos", productoService.findAll()); //recibe primero "productos" que seria la variable del home o del show, que va a llevar hacia la vista
        // como segundo parametro  usamos productoService  que con su metodo findall va a cargar  el primer parametro productos con los productos

        return "usuario/home";
    }
}
