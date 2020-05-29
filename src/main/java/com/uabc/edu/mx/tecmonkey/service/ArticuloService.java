package com.uabc.edu.mx.tecmonkey.service;

import com.uabc.edu.mx.tecmonkey.model.Articulo;
import com.uabc.edu.mx.tecmonkey.repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloService {
    @Autowired
    ArticuloRepository repository;

    public List<Articulo> getArticulos(){
        return (List<Articulo>) repository.findAll();
    }

    public Articulo getArticuloById(Long id){
        Optional<Articulo> articulo = repository.findById(id);
        if (articulo.isPresent()){
            return repository.findById(id).get();
        }
        return articulo.get();
    }
    public Articulo saveArticulo(Articulo a){
        if (a.getId()==null){
            a = repository.save(a);
            return a;
        }else{
            Optional<Articulo> articulo = repository.findById(a.getId());
            if(articulo.isPresent()){
                Articulo newArticulo = articulo.get();
                newArticulo.setId(a.getId());
                newArticulo.setTipo(a.getTipo());
                newArticulo.setNombre(a.getNombre());
                newArticulo.setDescripcion(a.getDescripcion());
                newArticulo.setCosto(a.getCosto());
                newArticulo.setImg(a.getImg());


                newArticulo=repository.save(newArticulo);
                return newArticulo;
            }else {
                a = repository.save(a);
                return a;
            }
        }
    }

    public void deleteArticulo(Long id){
        Optional<Articulo> articulo =repository.findById(id);
        if(articulo.isPresent()){
            repository.deleteById(id);
        }
    }
}
