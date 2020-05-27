package com.uabc.edu.mx.tecmonkey.repository;
import com.uabc.edu.mx.tecmonkey.model.Articulo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepository extends CrudRepository<Articulo, Long> {


}
