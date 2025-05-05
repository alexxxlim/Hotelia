package model;
//

import control.*;

//En este momento hacemos todas la clases sin getters ni setters (sin incapsulación) para facilitar nuestro trabajo con MVP
public class Usuario {

    public int id;
    public String nombre;
    public String correo;
    public String contrasenia;

    public Usuario(int id, String nombre, String correo, String contrasenia) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }
}
//TODO: @alexlim: falta funciones





/*
//Funciones principales:
    void login() {
//TODO:
}

void logout() {
//TODO:
    }

void actualizarPerfil(String contraseniaActual, String correoActual, String nuevaContrasenia, String nuevoCorreo) {
//TODO:
    }

boolean verificarCredenciales(String contraseña, String correo) {
//TODO:
    }

//Funciones adicionales:
String getRol() {
    }

boolean esAdministrador() {
    }

void cambiarContrasenia(String nuevaContrasenia) {
    }

void desactivarCuenta() {
    }

 */



