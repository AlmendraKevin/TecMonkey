package com.uabc.edu.mx.tecmonkey.repository;

import com.uabc.edu.mx.tecmonkey.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Long> {

}
