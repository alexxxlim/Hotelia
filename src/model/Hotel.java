package model;

import java.util.ArrayList;

public class Hotel {
    public int id;
    public String nombre;
    public String direccion;
    public int estrellas;

    public ArrayList<Habitacion> habitaciones;
    public ArrayList<Reserva> reservas;

    public Hotel(int id, String nombre, String direccion, int estrellas, ArrayList<Habitacion> habitaciones, ArrayList<Reserva> reservas) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.estrellas = estrellas;

        this.habitaciones = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

}
//TODO: @alexlim: falta funciones





/*
//Funciones principales:

    void agregarHabitacion(Habitacion h) {
//TODO:
}

    void agregarReservas(Reserva r) {
//TODO:
}

    List<Habitacion> buscarDisponibles(String tipo, String ini, String fin) {
//TODO:
}

    List<Reserva> reservasPorFecha(String dia) {
//TODO:
}

    List<Habitacion> habitacionesMasReservadas() {
//TODO:
}

//Funciones adicionales:
//TODO:

    boolean tieneReservaActiva() {
}

    Reserva obtenerReservaPorFecha(String fecha) {
}

    double calcularTotalGastado() {
}

    boolean esClienteFrecuente() {
}

    void solicitarAsistencia(String mensaje) {
}
*/