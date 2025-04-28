package control;

import java.util.List;

public class Huesped extends Usuario {
    List<Reserva> reservas;
    boolean socioVIP;

    public Huesped(){}
    public Huesped(String nombre, String correo, String contraseña, boolean socioVIP, List<Reserva> reservas){
        new Usuario(nombre, correo, contraseña);
        this.socioVIP = socioVIP;
        this.reservas = reservas;
    }
}
