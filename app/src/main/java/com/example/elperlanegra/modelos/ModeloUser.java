package com.example.elperlanegra.modelos;

public class ModeloUser {
    String nombre;
    String direccion;
    String correo;
    String contrasena;

    public ModeloUser() {
    }

    public ModeloUser(String nombre, String direccion, String correo, String contrasena) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
