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