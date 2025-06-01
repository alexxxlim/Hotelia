package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Habitacion {
    public int numero;
    public String tipo; // estándar, VIP, suite, etc.
    public double precio;
    public boolean esDisponible;

    public ArrayList<Resenia> resenias = new ArrayList<>();
    public ArrayList<Reserva> reservas = new ArrayList<>();

    public Habitacion(int numero, String tipo, double precio, boolean esDisponible,
                      ArrayList<Resenia> resenias, ArrayList<Reserva> reservas) {
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
        this.esDisponible = esDisponible;
        this.resenias = resenias != null ? resenias : new ArrayList<>();
        this.reservas = reservas != null ? reservas : new ArrayList<>();
    }

    //Getters
    public int getNumero(){
        return numero;
    }

    public String getTipo(){
        return tipo;
    }

    public double getPrecio(){
        return precio;
    }

    public boolean isEsDisponible(){
        return esDisponible;
    }

    public ArrayList<Resenia> getResenias() {
        return resenias;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    //Setters

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setEsDisponible(boolean esDisponible) {
        this.esDisponible = esDisponible;
    }

    public void setResenias(ArrayList<Resenia> resenias) {
        this.resenias = resenias;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    // Funciones Principales:

    public double calcularPrecioConDescuento(boolean esVip) {
        double descuento = 0.20;
        return esVip ? precio * (1 - descuento) : precio;
    }

    public double obtenerRatingPromedio() {
        if (resenias == null || resenias.isEmpty()) return 0.0;

        int suma = 0;
        for (Resenia r : resenias) {
            suma += r.rating;
        }
        return (double) suma / resenias.size();
    }

    public boolean estaDisponible(LocalDate inicio, LocalDate fin) {
        if (!esDisponible) return false;

        for (Reserva r : reservas) {
            boolean solapa = !(r.fechaFin.isBefore(inicio) || r.fechaInicio.isAfter(fin));
            if (solapa) return false;
        }
        return true;
    }


    public void agregarResenia(Resenia r) {
        resenias.add(r);
    }

    public void agregarReserva(Reserva r) {
        reservas.add(r);
    }

    public void marcarMantenimiento(boolean estado) {
        esDisponible = !estado;
    }

    public void limpiarReservasPasadas(LocalDate fechaActual) {
        Iterator<Reserva> iter = reservas.iterator();
        while (iter.hasNext()) {
            Reserva r = iter.next();
            if (r.fechaFin.isBefore(fechaActual)) {
                iter.remove();
            }
        }
    }

    public int totalNochesReservadas() {
        int total = 0;
        for (Reserva r : reservas) {
            total += r.dias;
        }
        return total;
    }

    public boolean isDisponibleHoy() {
        LocalDate hoy = LocalDate.now();
        return estaDisponible(hoy, hoy.plusDays(1));
    }
}