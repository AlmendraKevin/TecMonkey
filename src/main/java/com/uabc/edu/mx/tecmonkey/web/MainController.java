package com.uabc.edu.mx.tecmonkey.web;


import com.uabc.edu.mx.tecmonkey.service.ArticuloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    private ArticuloService service;
    @RequestMapping
    public String home(){
        return "Home";
    }

}
