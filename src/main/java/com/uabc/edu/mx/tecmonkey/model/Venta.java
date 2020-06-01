package com.uabc.edu.mx.tecmonkey.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String descripcionDeVenta;
    private float totalVenta;
    private Date fechaVenta;



}
