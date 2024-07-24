package com.complete.eCommerce.service;

import com.complete.eCommerce.model.Producto;
import com.complete.eCommerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService{
//aca vamos a implementar los metodos declarados en la interface  ProductoService

    @Autowired //inyecto a esta clase un objeto para obtener todos los metodos crud
     private ProductoRepository productoRepository;


    @Override
    public Producto save(Producto producto) {
        return  productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> get(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public void update(Producto producto) {
            productoRepository.save(producto);
    }

    @Override
    public void delete(Integer id) {
            productoRepository.deleteById(id);
    }
}
