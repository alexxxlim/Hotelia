package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        this.habitaciones = habitaciones != null ? habitaciones : new ArrayList<>();
        this.reservas = reservas != null ? reservas : new ArrayList<>();
    }

    //Getters

    public int getId(){
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    //Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // Funciones principales:

    public void agregarHabitacion(Habitacion h) {
        habitaciones.add(h);
    }

    public void agregarReservas(Reserva r) {
        reservas.add(r);
    }

    public List<Habitacion> buscarDisponibles(String tipo, LocalDate ini, LocalDate fin) {
        return habitaciones.stream()
                .filter(h -> h.tipo.equalsIgnoreCase(tipo) && h.estaDisponible(ini, fin))
                .collect(Collectors.toList());
    }

    public List<Reserva> reservasPorFecha(LocalDate dia) {
        return reservas.stream()
                .filter(r -> !r.fechaInicio.isAfter(dia) && !r.fechaFin.isBefore(dia))
                .collect(Collectors.toList());
    }

    public List<Habitacion> habitacionesMasReservadas() {
        return habitaciones.stream()
                .sorted(Comparator.comparingInt(h -> -h.reservas.size()))
                .collect(Collectors.toList());
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