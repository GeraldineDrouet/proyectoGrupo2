package com.example.elperlanegra.modelos;

public class ModeloCategoriasInicio {

    String nombre;
    String tipo;
    String img_url;

    public ModeloCategoriasInicio() {
    }

    public ModeloCategoriasInicio(String nombre, String tipo, String img_url) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.img_url = img_url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
