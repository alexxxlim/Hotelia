package control;

public class Gerente extends Usuario{
    public Gerente(){}

    public Gerente(String nombre, String contraseña, String correo){
        new Usuario(nombre, contraseña, correo);
    }

}
