package com.uabc.edu.mx.tecmonkey.web;

import com.uabc.edu.mx.tecmonkey.model.Articulo;
import com.uabc.edu.mx.tecmonkey.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    ArticuloService service;

    @RequestMapping
    public String venta (Model model){
      return "Catalogo";
    }
    @RequestMapping(path = { "/venta/{id}" })
    public String editArticuloById(Model model, @PathVariable(value = "id", required = true) Long id) {
        Articulo articulo = service.getArticuloById(id);
        model.addAttribute("articulo", articulo);
        return "ventaFormato";
    }
    @RequestMapping(path = "/ventaArticulo", method = RequestMethod.POST)
    public String saveOrUpdateArticulo(@RequestParam(value = "idArticulo", required = false) Optional<Long> id,
                                     @RequestParam(value = "tipoArticulo", required = true) String tipo,
                                     @RequestParam(value = "decripcion", required = true) String descripcion,
                                     @RequestParam(value = "precio", required = true) Integer precio,
                                     @RequestParam(value = "nombreArticulo", required = true) String nombreArticulo,
                                     @RequestParam(value = "img", required = false) MultipartFile img) {

        Articulo entity;

        if (id.isPresent()) {
            entity = service.getArticuloById(id.get());
        } else{
            entity = new Articulo(); //empty entity
        }
        entity.setTipo(tipo);
        entity.setDescripcion(descripcion);
        entity.setCosto(precio);
        entity.setNombre(nombreArticulo);
        entity.setImg(entity.getImg());
        entity.setStr(entity.getStr());
        service.saveArticulo(entity); //SAVE OR UPDATE SERVICE
        return "redirect:/";
    }

}
