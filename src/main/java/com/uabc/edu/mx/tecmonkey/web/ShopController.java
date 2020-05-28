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
        List<Articulo> articulo = service.getArticulos();
        List<Articulo> articuloAux = new ArrayList<Articulo>();
        articulo.forEach(articulo1 -> {
            if(!articulo1.isVentaArticulo()){ //Falta aqui que se va hacer con la venta del articulo bro
                articuloAux.add(articulo1);
          }
        });

        model.addAttribute("articulo", articuloAux);
        return "shop";
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
                                     @RequestParam(value = "clave", required = true) Integer clave,
                                     @RequestParam(value = "precio", required = true) Integer precio,
                                     @RequestParam(value = "nombreArticulo", required = true) String nombreArticulo,
                             //        @RequestParam(value = "nombreDelComprador", required = false) String responsable,
                                     @RequestParam(value = "img", required = false) MultipartFile img) {

        Articulo entity;

        if (id.isPresent()) {
            entity = service.getArticuloById(id.get());
        } else{
            entity = new Articulo(); //empty entity
        }
        entity.setTipoArticulo(tipo);
        entity.setClave(clave);
        entity.setPrecio(precio);
        entity.setNombreArticulo(nombreArticulo);
        entity.setImg(entity.getImg());
        entity.setStr(entity.getStr());
        service.saveArticulo(entity); //SAVE OR UPDATE SERVICE
        return "redirect:/";
    }
}
