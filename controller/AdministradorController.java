package com.complete.eCommerce.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Administrador")
public class AdministradorController {

    @GetMapping("")
    public String home(){
        return "Administrador/home";
    }



}
