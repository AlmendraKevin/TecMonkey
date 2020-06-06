package com.uabc.edu.mx.tecmonkey.web;

import com.uabc.edu.mx.tecmonkey.model.Articulo;
import com.uabc.edu.mx.tecmonkey.model.User;
import com.uabc.edu.mx.tecmonkey.service.ArticuloService;
import com.uabc.edu.mx.tecmonkey.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.uabc.edu.mx.tecmonkey.service.UsuarioService;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    ArticuloService service;

    @RequestMapping
    public String getArticulos (Model model){
        List<Articulo> articulos = service.getArticulos();
        model.addAttribute("articulo",articulos);
      return "ControlVentaAdmin";
    }
    @RequestMapping(path = { "/venta/{id}" })
    public String editArticuloById(Model model, @PathVariable(value = "id", required = true) Long id) {
        Articulo articulo = service.getArticuloById(id);
        model.addAttribute("articulo", articulo);
        return "FormVenta";
    }
    @RequestMapping(path = "/ventaArticulo", method = RequestMethod.POST)
    public String saveOrUpdateArticulo(@RequestParam(value = "id", required = false) Optional<Long> id,
                                     @RequestParam(value = "tipo", required = true) String tipo,
                                     @RequestParam(value = "descripcion", required = true) String descripcion,
                                     @RequestParam(value = "costo", required = true)  String precio,
                                     @RequestParam(value = "nombre", required = true) String nombreArticulo,
                                     @RequestParam(value = "img", required = false) MultipartFile img,
                                     @RequestParam(value = "existencia", required = false) String existencia){

        Articulo entity;

        if (id.isPresent()) {
            entity = service.getArticuloById(id.get());
        } else{
            entity = new Articulo(); //empty entity
        }
        entity.setTipo(tipo);
        entity.setDescripcion(descripcion);
        entity.setCosto(Float.parseFloat(precio));
        entity.setNombre(nombreArticulo);
        entity.setImg(entity.getImg());
        entity.setStr(entity.getStr());
        entity.setExistencia(Integer.parseInt(existencia));
        service.saveArticulo(entity); //SAVE OR UPDATE SERVICE
        return "redirect:/shop";
    }

    @RequestMapping("/ventauser")
    public String listaVenta (Model model){
        List<Articulo> articulos = service.getArticulos();

        model.addAttribute("articulo",articulos);
        return "ControlVentaAdmin";
    }

}
