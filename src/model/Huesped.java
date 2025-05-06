package model;

import java.util.ArrayList;
//import java.util.List;

public class Huesped extends Usuario {
    public int idVIP;
    public boolean esSocioVIP;
    public Habitacion habitacion;

    public ArrayList<Reserva> reservas = new ArrayList<>();

    public Huesped(int id, int idVIP, String nombre, String correo, String contrasenia, boolean esSocioVIP) {
        super(id, nombre, correo, contrasenia);
        this.idVIP = idVIP;
        this.esSocioVIP = esSocioVIP;

        this.reservas = new ArrayList<>();
    }

    public Huesped(int id, String nombre, String correo, String contrasenia, int idVIP, boolean esSocioVIP, Habitacion habitacion, ArrayList<Reserva> reservas) {
        super(id, nombre, correo, contrasenia);
        this.idVIP = idVIP;
        this.esSocioVIP = esSocioVIP;
        this.habitacion = habitacion;

        this.reservas = new ArrayList<>();
    }
}
//TODO: @alexlim: falta funciones



/*
//Funciones principales:

    Reserva hacerReserva(Habitacion h, String inicio, String fin) {
//TODO:
}

    void cancelarReserva(Reserva r) {
//TODO:
}

    void dejarResenia(Habitacion h, String comentario, int rating) {
//TODO:
}

    void pagarMembresiaVIP() {
//TODO:
}

    List<Habitacion> verHistorialReservas() {
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