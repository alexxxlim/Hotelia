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





    /*
    void login() {

    }

    void logout() {

    }

    void actualizarPerfil(String contraseñaActual, String correoActual, String nuevaContraseña, String nuevoCorreo) {

    }

    boolean verificarCredenciales(String contraseña, String correo) {

    }
     */



