package model;

import java.util.ArrayList;

public class Recepcionista extends Usuario {
    public Huesped huesped;
    public Habitacion habitacion;

    public ArrayList<Reserva> reservas;
    //public ArrayList<Permiso> permisos;

    public Recepcionista(int id, String nombre, String correo, String contrasenia) {
        super(id, nombre, correo, contrasenia);
        this.huesped = huesped;

        this.reservas = new ArrayList<>();
        //this.permisos = new ArrayList<>();
    }
}
//TODO: @alexlim: falta funciones





/*
//Funciones Principales:

    List<Reserva> verReservasActivas() {
    //TODO:
}

    void asistirCliente(Huesped h) {
    //TODO:
}

    Habitacion buscarHabitacionDisponible() {
    //TODO:
}

    void crearReservaParaCliente(Huesped h, Habitacion hab, LocalDate ini, LocalDate fin) {
    //TODO:
}

//Funciones adicionales:
//TODO:

    public void confirmarReserva(Reserva r) {
}

    public void cancelarReservaCliente(Reserva r) {
}
 */

