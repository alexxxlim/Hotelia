package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    public int id;
    public LocalDate fechaInicio;
    public LocalDate fechaFin;
    public int dias;
    public boolean esConfirmada;
    public Huesped huesped;
    public Habitacion habitacion;

    public Reserva(int id, LocalDate fechaInicio, LocalDate fechaFin, int dias, boolean esConfirmada, Huesped huesped, Habitacion habitacion) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.dias = dias;
        this.esConfirmada = esConfirmada;
        this.huesped = huesped;
        this.habitacion = habitacion;
    }

    // Funciones principales:

    public double calcularPrecioTotal() {
        long noches = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        double precioPorNoche = habitacion.calcularPrecioConDescuento(huesped.esSocioVIP);
        return noches * precioPorNoche;
    }

    public void confirmarReserva() {
        if (esConfirmada) {
            throw new IllegalStateException("La reserva ya está confirmada.");
        }
        if (!habitacion.estaDisponible(fechaInicio, fechaFin)) {
            throw new IllegalStateException("La habitación no está disponible entre las fechas indicadas.");
        }
        esConfirmada = true;
        habitacion.reservas.add(this);
        huesped.reservas.add(this);
    }

    public void cancelarReserva() {
        if (!esConfirmada) {
            throw new IllegalStateException("La reserva no está confirmada.");
        }
        esConfirmada = false;
        habitacion.reservas.remove(this);
        huesped.reservas.remove(this);
    }
}