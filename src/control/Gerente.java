package control;

public class Gerente extends Usuario{
    public Gerente(){
        super();
    }

    public Gerente(String nombre, String contraseña, String correo){
        new Usuario(nombre, contraseña, correo);
    }

}
