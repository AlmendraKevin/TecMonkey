package com.uabc.edu.mx.tecmonkey.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacto")
public class Contacto {
    @RequestMapping
    public String contacto(){
        return "Contacto";
    }
    @RequestMapping("/promociones")
    public String promociones(){
        return "Promociones";
    }
}
