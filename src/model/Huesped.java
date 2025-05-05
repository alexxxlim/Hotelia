package model;
//

import java.util.ArrayList;
//import java.util.List;

public class Huesped extends Usuario {
    public int idVIP;
    public boolean esSocioVIP;

    public ArrayList<Reserva> reservas = new ArrayList<>();

    public Huesped(int id, int idVIP, String nombre, String correo, String contrasenia, boolean esSocioVIP) {
        super(id, nombre, correo, contrasenia);
        this.idVIP = idVIP;
        this.esSocioVIP = esSocioVIP;
    }
}