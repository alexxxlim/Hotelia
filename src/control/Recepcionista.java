package control;

public class Recepcionista {

    public Recepcionista(){

    }
    public Recepcionista(String nombre, String correo, String contraseña){
        new Usuario(nombre, correo, contraseña);
    }
}
