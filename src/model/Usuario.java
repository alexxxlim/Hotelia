package model;

public class Usuario {

    public int id;
    public String nombre;
    public String correo;
    public String contrasenia;
    public boolean activo = true;

    //Getters
    public int getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public String getCorreo(){
        return correo;
    }

    public String getContrasenia(){
        return contrasenia;
    }

    public boolean isActivo(){
        return activo;
    }

    //Setters
    public void setId(int ID){
        this.id = ID;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public void setContrasenia(String contrasenia){
        this.contrasenia = contrasenia;
    }

    public void setActivo(boolean activo){
        this.activo = activo;
    }



    public Usuario(int id, String nombre, String correo, String contrasenia) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    // Funciones adicionales:

    public String getRol() {
        return this.getClass().getSimpleName();
    }

    public boolean esAdministrador() {
        return this instanceof Gerente;
    }

    public void cambiarContrasenia(String nuevaContrasenia) {
        this.contrasenia = nuevaContrasenia;
    }

    public void desactivarCuenta() {
        this.activo = false;
    }
}

/*
//Funciones adicionales:
//TODO:

String getRol() {
    }

boolean esAdministrador() {
    }

void cambiarContrasenia(String nuevaContrasenia) {
    }

void desactivarCuenta() {
    }

 */