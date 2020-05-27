package com.uabc.edu.mx.tecmonkey.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;
@Entity
@Table(name="ARTICULO")
public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticulo;
    @Column(name = "nombre_Articulo")
    private String nombreArticulo;
    @Column(name = "tipo_articulo")
    private String tipoArticulo;
    @Column(name = "clave")
    private Integer clave;
    @Column(name = "precio")
    private Integer precio;
    @Column(name = "img")
    private byte[] img;
    @Column(name = "str")
    private String str;

    public Long getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Long idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public String getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(String tipoArticulo) {
        this.tipoArticulo = tipoArticulo;
    }

    public Integer getClave() {
        return clave;
    }

    public void setClave(Integer clave) {
        this.clave = clave;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
    public  String toString(){
    return "Articulo{" +
            "idArticulo=" + idArticulo +
            ", tipoArticulo='" + tipoArticulo + '\'' +
            ", nombreArticulo='" + nombreArticulo + '\'' +
            ", clave='" + clave + '\'' +
            ", precio=" + precio +
            ", img=" + Arrays.toString(img) +
            ", str='" + str + '\'' +
            '}';
    }
}
