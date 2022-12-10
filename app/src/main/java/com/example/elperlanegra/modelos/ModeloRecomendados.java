package com.example.elperlanegra.modelos;

public class ModeloRecomendados {
    String nombre;
    String descripcion;
    String raiting;
    String img_url;
    String precio;

    public ModeloRecomendados() {
    }

    public ModeloRecomendados(String nombre, String descripcion, String raiting, String img_url, String precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.raiting = raiting;
        this.img_url = img_url;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRaiting() {
        return raiting;
    }

    public void setRaiting(String raiting) {
        this.raiting = raiting;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
