package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Huesped extends Usuario {
    public int idVIP;
    public boolean esSocioVIP;
    public Habitacion habitacion;

    public ArrayList<Reserva> reservas = new ArrayList<>();

    public Huesped(int id, int idVIP, String nombre, String correo, String contrasenia, boolean esSocioVIP) {
        super(id, nombre, correo, contrasenia);
        this.idVIP = idVIP;
        this.esSocioVIP = esSocioVIP;
    }

    public Huesped(int id, String nombre, String correo, String contrasenia, int idVIP, boolean esSocioVIP, Habitacion habitacion, ArrayList<Reserva> reservas) {
        super(id, nombre, correo, contrasenia);
        this.idVIP = idVIP;
        this.esSocioVIP = esSocioVIP;
        this.habitacion = habitacion;
        this.reservas = reservas != null ? reservas : new ArrayList<>();
    }

    //Getters
    public int getIdVIP(){
        return idVIP;
    }

    public boolean isEsSocioVIP(){
        return esSocioVIP;
    }

    public Habitacion getHabitacion(){
        return habitacion;
    }

    public ArrayList<Reserva> getReservas(){
        return reservas;
    }

    //Setters
    public void setIdVIP(int idVIP){
        this.idVIP = idVIP;
    }

    public void setEsSocioVIP(boolean esSocioVIP){
        this.esSocioVIP = esSocioVIP;
    }

    public void setHabitacion(Habitacion habitacion){
        this.habitacion = habitacion;
    }

    public void setReservas(ArrayList<Reserva> reservas){
        this.reservas = reservas;
    }

    // Funciones principales:

    public Reserva hacerReserva(Habitacion h, LocalDate inicio, LocalDate fin) {
        if (!h.estaDisponible(inicio, fin)) {
            throw new IllegalStateException("La habitación no está disponible para las fechas indicadas.");
        }
        int dias = (int) (fin.toEpochDay() - inicio.toEpochDay());
        Reserva r = new Reserva(-1, inicio, fin, dias, false, this, h); // ID asignado después
        r.confirmarReserva();
        return r;
    }

    public void cancelarReserva(Reserva r) {
        r.cancelarReserva();
    }

    public void dejarResenia(Habitacion h, String comentario, int rating) {
        Resenia nueva = new Resenia(comentario, rating, this);
        h.agregarResenia(nueva);
    }

    public void pagarMembresiaVIP() {
        this.esSocioVIP = true;
        System.out.println("¡Felicidades! Ahora eres socio VIP.");
    }

    public List<Habitacion> verHistorialReservas() {
        List<Habitacion> historial = new ArrayList<>();
        for (Reserva r : reservas) {
            historial.add(r.habitacion);
        }
        return historial;
    }


}

/*
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