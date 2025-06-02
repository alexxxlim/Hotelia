package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Clase que representa una reserva en el sistema Hotelia.
 *
 * Una reserva contiene información sobre las fechas de inicio y fin,
 * el número de días, estado de confirmación, y las referencias al huésped
 * y habitación asociadas.
 *
 * Esta clase también permite confirmar, cancelar y calcular el precio de la reserva.
 *
 * @author
 * @version 1.0
 */
public class Reserva {

    private int id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int dias;
    private boolean esConfirmada;
    private Huesped huesped;
    private Habitacion habitacion;

    /**
     * Constructor de reserva.
     *
     * @param id Identificador único.
     * @param fechaInicio Fecha de entrada.
     * @param fechaFin Fecha de salida.
     * @param dias Número total de noches.
     * @param esConfirmada Estado de confirmación.
     * @param huesped Objeto {@link Huesped} que realiza la reserva.
     * @param habitacion Objeto {@link Habitacion} reservada.
     */
    public Reserva(int id, LocalDate fechaInicio, LocalDate fechaFin, int dias, boolean esConfirmada, Huesped huesped, Habitacion habitacion) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.dias = dias;
        this.esConfirmada = esConfirmada;
        this.huesped = huesped;
        this.habitacion = habitacion;
    }

    // ========================
    // Getters
    // ========================

    public int getId() {
        return id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public int getDias() {
        return dias;
    }

    public boolean isEsConfirmada() {
        return esConfirmada;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    // ========================
    // Setters
    // ========================

    public void setId(int id) {
        this.id = id;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public void setEsConfirmada(boolean esConfirmada) {
        this.esConfirmada = esConfirmada;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    // ========================
    // Funcionalidad
    // ========================

    /**
     * Calcula el precio total de la reserva en base a las noches
     * y si el huésped es socio VIP (se aplica descuento).
     *
     * @return Precio total en euros.
     */
    public double calcularPrecioTotal() {
        long noches = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        double precioPorNoche = habitacion.calcularPrecioConDescuento(huesped.isEsSocioVIP());
        return noches * precioPorNoche;
    }

    /**
     * Confirma la reserva, añadiéndola tanto a la habitación como al huésped.
     *
     * @throws IllegalStateException si la reserva ya está confirmada o
     *         si la habitación no está disponible en las fechas indicadas.
     */
    public void confirmarReserva() {
        if (esConfirmada) {
            throw new IllegalStateException("La reserva ya está confirmada.");
        }
        if (!habitacion.estaDisponible(fechaInicio, fechaFin)) {
            throw new IllegalStateException("La habitación no está disponible entre las fechas indicadas.");
        }
        esConfirmada = true;
        habitacion.getReservas().add(this);
        huesped.getReservas().add(this);
    }

    /**
     * Cancela la reserva confirmada, eliminándola de las listas de huésped y habitación.
     *
     * @throws IllegalStateException si la reserva aún no ha sido confirmada.
     */
    public void cancelarReserva() {
        if (!esConfirmada) {
            throw new IllegalStateException("La reserva no está confirmada.");
        }
        esConfirmada = false;
        habitacion.getReservas().remove(this);
        huesped.getReservas().remove(this);
    }
}