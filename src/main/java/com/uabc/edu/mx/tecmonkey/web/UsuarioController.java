package com.uabc.edu.mx.tecmonkey.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @RequestMapping("/nuevo")
    public String home(){
        return "RegistroUsuario";
    }



}
