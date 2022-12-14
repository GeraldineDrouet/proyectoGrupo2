package com.example.elperlanegra.modelos;

public class ModeloVerTodo {
    String nombre;
    String descripcion;
    String raiting;
    String img_url;
    String tipo;
    int precio;

    public ModeloVerTodo() {
    }

    public ModeloVerTodo(String nombre, String descripcion, String raiting, String img_url, int precio, String tipo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.raiting = raiting;
        this.img_url = img_url;
        this.precio = precio;
        this.tipo = tipo;
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
