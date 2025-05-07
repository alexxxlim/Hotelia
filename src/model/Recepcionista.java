package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Recepcionista extends Usuario {
    public Huesped huesped;
    public Habitacion habitacion;

    public ArrayList<Reserva> reservas;

    public Recepcionista(int id, String nombre, String correo, String contrasenia) {
        super(id, nombre, correo, contrasenia);
        this.reservas = new ArrayList<>();
    }

    //Getters

    public Huesped getHuesped() {
        return huesped;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    //Setters

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    // Funciones Principales:

    public List<Reserva> verReservasActivas() {
        List<Reserva> activas = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.esConfirmada) {
                activas.add(r);
            }
        }
        return activas;
    }

    public void asistirCliente(Huesped h) {
        System.out.println("Asistiendo al cliente: " + h.nombre);
    }

    public Habitacion buscarHabitacionDisponible(List<Habitacion> habitaciones, String tipo, LocalDate ini, LocalDate fin) {
        for (Habitacion h : habitaciones) {
            if (h.tipo.equalsIgnoreCase(tipo) && h.estaDisponible(ini, fin)) {
                return h;
            }
        }
        return null;
    }

    public void crearReservaParaCliente(Huesped h, Habitacion hab, LocalDate ini, LocalDate fin) {
        if (!hab.estaDisponible(ini, fin)) {
            System.out.println("La habitación no está disponible para las fechas indicadas.");
            return;
        }
        int dias = (int) (fin.toEpochDay() - ini.toEpochDay());
        Reserva r = new Reserva(-1, ini, fin, dias, false, h, hab);
        r.confirmarReserva();
        reservas.add(r);
        System.out.println("Reserva creada y confirmada para: " + h.nombre);
    }
}

/*
//Funciones adicionales:
//TODO:

    public void confirmarReserva(Reserva r) {
}

    public void cancelarReservaCliente(Reserva r) {
}
 */

