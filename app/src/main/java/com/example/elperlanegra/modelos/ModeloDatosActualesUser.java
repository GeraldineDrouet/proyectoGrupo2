package com.example.elperlanegra.modelos;

public class ModeloDatosActualesUser {
    String nombre;
    String direccion;
    String correo;
    String telefono;

    public ModeloDatosActualesUser() {
    }

    public ModeloDatosActualesUser(String nombre, String direccion, String correo, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
