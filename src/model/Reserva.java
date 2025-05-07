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

    //Getters

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public int getDias() {
        return dias;
    }

    public int getId() {
        return id;
    }

    public boolean isEsConfirmada() {
        return esConfirmada;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    //Setters

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public void setEsConfirmada(boolean esConfirmada) {
        this.esConfirmada = esConfirmada;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
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