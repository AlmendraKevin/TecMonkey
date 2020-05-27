package com.uabc.edu.mx.tecmonkey.web;


import com.uabc.edu.mx.tecmonkey.model.Articulo;
import com.uabc.edu.mx.tecmonkey.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/control")
public class ArticuloController {
    @Autowired
    ArticuloService service;

    @RequestMapping //class no path + method no path  = home index
    public String getArticulos(Model model) { //adds the model to load atributes in the html template
        List<Articulo> articulo = service.getArticulos();
        model.addAttribute("articulo", articulo);
        return "search";

    }

    @RequestMapping("/new") //displays empty form
    public String getAdmin(Model model) {// model to hold data on the frontend
        model.addAttribute("animal", new Articulo());
        return "formCreate";
    }
    @RequestMapping("/up")
    public String update(){
        return "formCreate";
    }

    @RequestMapping(path = { "/update/{id}" })
    public String editArticuloById(Model model, @PathVariable(value = "id", required = true) Long id) {
        Articulo articulo = service.getArticuloById(id);
        model.addAttribute("articulo", articulo);
        return "formCreate";
    }

    @GetMapping("/delete/{id}")
    public String deleteArticulo(@PathVariable("id") Long id) {
        service.deleteArticulo(id);
        return "redirect:/control";
    }
    @RequestMapping(path = "/saveArticulo", method = RequestMethod.POST)
    public String saveOrUpdateArticulo(@RequestParam(value = "idArticulo", required = false) Optional<Long> id,
                                     @RequestParam(value = "tipoArticulo", required = true) String tipo,
                                     @RequestParam(value = "clave", required = true) Integer clave,
                                     @RequestParam(value = "precio", required = true) Integer precio,
                                     @RequestParam(value = "nombreArticulo", required = true) String nombreArticulo,
                         //            @RequestParam(value = "variables", required = true) String NVariable,
                                     @RequestParam(value = "img", required = false) MultipartFile img) {

        Articulo entity;

        if (id.isPresent()) {
            entity = service.getArticuloById(id.get());
        } else{
            entity = new Articulo(); //empty entity
        }
        entity.setTipoArticulo(tipo);
        entity.setNombreArticulo(nombreArticulo);
        entity.setPrecio(precio);
        entity.setClave(clave);


        try {
            entity.setImg(img.getBytes()); //MultipartFile to byte[] and stored as longblob
        } catch (Exception e) {
            System.out.println("Fallo la conversion " + e); //conversion failed
        }
        entity.setStr(Base64.getEncoder().encodeToString(entity.getImg()));
        service.saveArticulo(entity); //SAVE OR UPDATE SERVICE
        return "redirect:/control";
    }
}
