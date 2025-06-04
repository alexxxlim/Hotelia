package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase que representa una habitación dentro del sistema Hotelia.
 *
 * Una habitación puede tener un número, tipo, precio, estado de disponibilidad,
 * así como una lista de reseñas y reservas asociadas.
 *
 * Esta clase ofrece métodos para calcular ratings, gestionar disponibilidad,
 * aplicar descuentos y realizar operaciones sobre las reservas.
 *
 * @author
 * @version 1.0
 */
public class Habitacion {

    private int numero;
    private String tipo;
    private double precio;
    private boolean esDisponible;

    private ArrayList<Resenia> resenias = new ArrayList<>();
    private ArrayList<Reserva> reservas = new ArrayList<>();

    /**
     * Constructor de habitación.
     *
     * @param numero Número identificador de la habitación.
     * @param tipo Tipo de habitación (Ej: estándar, VIP, suite).
     * @param precio Precio base de la habitación.
     * @param esDisponible Estado de disponibilidad general.
     * @param resenias Lista inicial de reseñas (puede ser null).
     * @param reservas Lista inicial de reservas (puede ser null).
     */
    public Habitacion(int numero, String tipo, double precio, boolean esDisponible,
                      ArrayList<Resenia> resenias, ArrayList<Reserva> reservas) {
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
        this.esDisponible = esDisponible;
        this.resenias = resenias != null ? resenias : new ArrayList<>();
        this.reservas = reservas != null ? reservas : new ArrayList<>();
    }

    // ========================
    // Getters
    // ========================

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean isEsDisponible() {
        return esDisponible;
    }

    public ArrayList<Resenia> getResenias() {
        return resenias;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    // ========================
    // Setters
    // ========================

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

    // ========================
    // Funcionalidad Principal
    // ========================

    /**
     * Calcula el precio total con descuento si el cliente es VIP.
     *
     * @param esVip Indica si el cliente tiene membresía VIP.
     * @return Precio con descuento aplicado si corresponde.
     */
    public double calcularPrecioConDescuento(boolean esVip) {
        double descuento = 0.20;
        return esVip ? precio * (1 - descuento) : precio;
    }

    /**
     * Calcula el promedio de calificación de la habitación basado en las reseñas.
     *
     * @return Promedio de calificación o 0.0 si no hay reseñas.
     */
    public double obtenerRatingPromedio() {
        if (resenias == null || resenias.isEmpty()) return 0.0;

        int suma = 0;
        for (Resenia r : resenias) {
            suma += r.getRating();
        }
        return (double) suma / resenias.size();
    }

    /**
     * Verifica si la habitación está disponible en el rango de fechas indicado.
     *
     * @param inicio Fecha de inicio de la reserva.
     * @param fin Fecha de fin de la reserva.
     * @return {@code true} si no hay reservas que se solapen y la habitación está disponible.
     */
    public boolean estaDisponible(LocalDate inicio, LocalDate fin) {
        if (!esDisponible) {
            return false;
        }

        for (Reserva r : reservas) {
            // Hay solapamiento si el rango solicitado comparte al menos un día
            // con una reserva existente. Permite reservas contiguas.
            boolean solapa = inicio.isBefore(r.getFechaFin()) && fin.isAfter(r.getFechaInicio());
            if (solapa) {
                return false;
            }
        }
        return true;
    }

    /**
     * Agrega una nueva reseña a la habitación.
     *
     * @param r Objeto {@link Resenia} a añadir.
     */
    public void agregarResenia(Resenia r) {
        resenias.add(r);
    }

    /**
     * Agrega una nueva reserva a la habitación.
     *
     * @param r Objeto {@link Reserva} a añadir.
     */
    public void agregarReserva(Reserva r) {
        reservas.add(r);
    }

    /**
     * Cambia el estado de disponibilidad de la habitación por motivos de mantenimiento.
     *
     * @param estado {@code true} si entra en mantenimiento (no disponible).
     */
    public void marcarMantenimiento(boolean estado) {
        esDisponible = !estado;
    }

    /**
     * Elimina todas las reservas pasadas (anteriores a la fecha actual) de la lista.
     *
     * @param fechaActual Fecha de referencia para comparar.
     */
    public void limpiarReservasPasadas(LocalDate fechaActual) {
        Iterator<Reserva> iter = reservas.iterator();
        while (iter.hasNext()) {
            Reserva r = iter.next();
            if (r.getFechaFin().isBefore(fechaActual)) {
                iter.remove();
            }
        }
    }

    /**
     * Calcula el total de noches reservadas en esta habitación.
     *
     * @return Total acumulado de días entre todas las reservas activas.
     */
    public int totalNochesReservadas() {
        int total = 0;
        for (Reserva r : reservas) {
            total += r.getDias();
        }
        return total;
    }

    /**
     * Verifica si la habitación está disponible para la fecha de hoy.
     *
     * @return {@code true} si no hay reservas activas para hoy.
     */
    public boolean isDisponibleHoy() {
        LocalDate hoy = LocalDate.now();
        return estaDisponible(hoy, hoy.plusDays(1));
    }
}