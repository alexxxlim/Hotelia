package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Habitacion {
    public int numero;
    public String tipo; // estándar, VIP, suite, etc.
    public double precio;
    public boolean esDisponible;
    //public boolean mantenimiento;

    public ArrayList<Resenia> resenias = new ArrayList<>();
    public ArrayList<Reserva> reservas = new ArrayList<>();

    public Habitacion(int numero, String tipo, double precio, boolean esDisponible/*, boolean mantenimiento*/,
                      ArrayList<Resenia> resenias, ArrayList<Reserva> reservas) {
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
        this.esDisponible = esDisponible;
        //this.mantenimiento = mantenimiento;

        this.resenias = new ArrayList<>();
    }

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
}
//TODO: @alexlim: falta funciones





/*
//Funciones Principales:

    public void agregarResenia(Resenia r) {
//TODO:
}

    public void agregarReserva(Reserva r) {
//TODO:
}

    public void marcarMantenimiento(boolean estado) {
//TODO:
}

    public void limpiarReservasPasadas(String fechaActual) {
//TODO:
}

    public int totalNochesReservadas() {
//TODO:
}
*/