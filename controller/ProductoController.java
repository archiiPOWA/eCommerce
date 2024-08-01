package com.complete.eCommerce.controller;
import com.complete.eCommerce.model.Producto;
import com.complete.eCommerce.model.Usuario;
import com.complete.eCommerce.service.ProductoService;
import com.complete.eCommerce.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
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

    //INYECTO EL OBJETO/VARIABLE  para traer los metodos declarados en el Servicio
    @Autowired
    private UploadFileService upload;


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
    public String save(Producto producto, @RequestParam("img") MultipartFile  file) throws IOException {
        LOGGER.info("Este es el objeto producto {}", producto);
        Usuario u = new Usuario (1,"","","","", "", "", "");

        //IMAGEN
        //hago la logica para subir imagenes al servidor y tambien guardar el nombre de la imagen en la base de datos en tabla producto
        if(producto.getId()==null){//cuando se crea un producto
            String nombreImagen = upload.saveImage(file); //---> declaro el file, de tipo MultipartFile en los parametros del metodo
            producto.setImagen(nombreImagen);
            }else{

        }
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
    public String update(Producto producto, @RequestParam("img") MultipartFile  file) throws IOException { //como parametros recibe un objeto producto de tipo producto
        Producto prod = new Producto();
        prod = productoService.get(producto.getId()).get();

        if (file.isEmpty()) { // cuando editamos el producto pero no cambiamos la imagen

            producto.setImagen(prod.getImagen());
        }else{ //el caso en que cambiamos la imagen al editar el producto
            //aca traigo un fragmento de codigo del metodo Delete
                       //eliminar cuando no sea la imagen por defecto
            if (!prod.getImagen().equals ("default.jpg")){
                upload.deleteImage(prod.getImagen());
            }
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        //aca en la clase 18 - refactorizamos el codigo sacando variables locales a globales y solucionamos el problema de traer user_id
        producto.setUsuario(prod.getUsuario());
        productoService.update(producto);
        return "redirect:/productos";
    }
    //Seguidamente programamos la funcion para eliminar productos
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){ //con Pathvariable hacmeos el mapeo de la variable que recibimos en la url  "{id} y la pase al parametro  --> Integer id
        Producto prod = new Producto();
        prod = productoService.get(id).get();
        //eliminar cuando no sea la imagen por defecto
        if (!prod.getImagen().equals ("default.jpg")){
            upload.deleteImage(prod.getImagen());
        }
        productoService.delete(id);
        return "redirect:/productos";
    }

}
