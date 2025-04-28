package control;

public class Usuario {
    String nombre;
    String correo;
    String contraseña;

    public Usuario(){

    }
    public Usuario(String nombre, String correo, String contraseña){
        this.nombre = nombre;
        this.correo= correo;
        this.contraseña = contraseña;
    }

    void login(){

    }

    void logout(){

    }

    void actualizarPerfil(String contraseñaActual, String correoActual, String nuevaContraseña, String nuevoCorreo){

    }

    boolean verificarCredenciales(String contraseña, String correo){

    }
}
