package com.uabc.edu.mx.tecmonkey.web;

import com.uabc.edu.mx.tecmonkey.model.User;
import com.uabc.edu.mx.tecmonkey.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @RequestMapping
    public String getUsuarios(Model model){
        List<User> user = usuarioService.getUsuarios() ;
        model.addAttribute("user",user);
        return "ControlUsuariosAdmin";
    }
    @RequestMapping("/new")
    public String guardarRegistro(){
        return "FormUsuarioAdmin";
    }

    @RequestMapping("/nuevo")
    public String crearNuevo(){
        return "RegistroUsuario";
    }



}
