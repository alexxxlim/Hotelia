package model;
//

import control.*;

import java.util.ArrayList;
//import java.util.List;

public class Huesped extends Usuario {
    public boolean socioVIP;
    public ArrayList<Reserva> reservas = new ArrayList<>();

    public Huesped(int id, String nombre, String correo, String contrasenia, boolean socioVIP) {
        super(id, nombre, correo, contrasenia);
        this.socioVIP = socioVIP;
    }
}