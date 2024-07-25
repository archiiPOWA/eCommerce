package com.complete.eCommerce.controller;
import com.complete.eCommerce.model.Producto;
import com.complete.eCommerce.model.Usuario;
import com.complete.eCommerce.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {
private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    //para implementar los metodos crus de jpa
    //inyectamos una variable, que en realidad es un objeto
    //de tipo ProductoService objeto que tendra todos los metodos
    //que necesitamos de JPA
    @Autowired
    private ProductoService productoService;

    @GetMapping("")
    public String show(Model model){ //el objeto model lleva informacion desde el backend hacia la vista
        model.addAttribute("productos", productoService.findAll());
        return "productos/show";
    }

    @GetMapping("/create")
    public String create(){
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto) {
        LOGGER.info("Este es el objeto producto {}", producto);
        Usuario u = new Usuario (1,"","","","", "", "", "");
        productoService.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Producto producto = new Producto();
        Optional<Producto> optionalProducto = productoService.get(id);
        producto = optionalProducto.get();

        LOGGER.info("producto buscado: {}", producto);
        //con  la siguiente linea traemos la informacion del backend a la vista para poder editar el producto
        model.addAttribute("p", producto); //el objeto model invoca al metodo addAtribute  que recibe la variable "p" de producto para que la lleve a la vista, y en el segundo parametro el valor de lo contiene producto

        return "productos/edit";
    }
//ahora creo el metodo para realizar la actualizacion como tal
    @PostMapping("/update")
    public String update(Producto producto) { //como parametros recibe un objeto producto de tipo producto
        productoService.update(producto);
        return "redirect:/productos";
    }
    //Seguidamente programamos la funcin para eliminar productos
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){ //con Pathvariable hacmeos el mapeo de la variable que recibimos en la url  "{id} y la pase al parametro  --> Integer id
        productoService.delete(id);
        return "redirect:/productos";
    }

}
