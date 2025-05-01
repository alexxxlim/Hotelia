package control;

public class Recepcionista extends Usuario{

    public Recepcionista(){
       super();
    }
    public Recepcionista(String nombre, String correo, String contraseña){
        new Usuario(nombre, correo, contraseña);
    }
}
