package com.uabc.edu.mx.tecmonkey.web;


import com.uabc.edu.mx.tecmonkey.model.User;
import com.uabc.edu.mx.tecmonkey.service.ArticuloService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    private ArticuloService service;

    @RequestMapping
    public String home() {
        return "Inicio";
    }


    @RequestMapping("/contacto")
    public String contacto() {
        return "Contacto";
    }

    @RequestMapping("/promociones")
    public String promociones() {
        return "Promociones";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("usuario", new User());
        return "Login";
    }

}
