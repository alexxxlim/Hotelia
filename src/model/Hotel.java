package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase que representa un hotel dentro del sistema Hotelia.
 *
 * Un hotel contiene un conjunto de habitaciones y reservas, y dispone de información
 * como su nombre, dirección, número de estrellas, etc.
 *
 * Proporciona métodos para gestionar habitaciones y reservas, así como para consultar
 * disponibilidad y estadísticas relacionadas con las reservas.
 *
 * @author
 * @version 1.0
 */
public class Hotel {

    private int id;
    private String nombre;
    private String direccion;
    private int estrellas;

    private ArrayList<Habitacion> habitaciones;
    private ArrayList<Reserva> reservas;

    /**
     * Constructor principal del hotel.
     *
     * @param id Identificador único del hotel.
     * @param nombre Nombre comercial del hotel.
     * @param direccion Dirección física del hotel.
     * @param estrellas Número de estrellas (clasificación).
     * @param habitaciones Lista de habitaciones disponibles (puede ser null).
     * @param reservas Lista de reservas registradas (puede ser null).
     */
    public Hotel(int id, String nombre, String direccion, int estrellas, ArrayList<Habitacion> habitaciones, ArrayList<Reserva> reservas) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.estrellas = estrellas;
        this.habitaciones = habitaciones != null ? habitaciones : new ArrayList<>();
        this.reservas = reservas != null ? reservas : new ArrayList<>();
    }

    // ========================
    // Getters
    // ========================

    public int getId() {
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

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    // ========================
    // Setters
    // ========================

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    // ========================
    // Funcionalidad Principal
    // ========================

    /**
     * Agrega una nueva habitación al hotel.
     *
     * @param h Objeto {@link Habitacion} a añadir.
     */
    public void agregarHabitacion(Habitacion h) {
        habitaciones.add(h);
    }

    /**
     * Agrega una nueva reserva al hotel.
     *
     * @param r Objeto {@link Reserva} a registrar.
     */
    public void agregarReservas(Reserva r) {
        reservas.add(r);
    }

    /**
     * Busca habitaciones disponibles de un tipo específico dentro de un rango de fechas.
     *
     * @param tipo Tipo de habitación (ej. "VIP", "Estándar").
     * @param ini Fecha de inicio de la estancia.
     * @param fin Fecha de fin de la estancia.
     * @return Lista de habitaciones disponibles que cumplen los criterios.
     */
    public List<Habitacion> buscarDisponibles(String tipo, LocalDate ini, LocalDate fin) {
        return habitaciones.stream()
                .filter(h -> h.getTipo().equalsIgnoreCase(tipo) && h.estaDisponible(ini, fin))
                .collect(Collectors.toList());
    }

    /**
     * Devuelve la lista de reservas activas en una fecha específica.
     *
     * @param dia Día a consultar.
     * @return Lista de reservas que cubren el día indicado.
     */
    public List<Reserva> reservasPorFecha(LocalDate dia) {
        return reservas.stream()
                .filter(r -> !r.getFechaInicio().isAfter(dia) && !r.getFechaFin().isBefore(dia))
                .collect(Collectors.toList());
    }

    /**
     * Devuelve una lista de habitaciones ordenadas por cantidad de reservas, de mayor a menor.
     *
     * @return Lista de habitaciones ordenadas por popularidad.
     */
    public List<Habitacion> habitacionesMasReservadas() {
        return habitaciones.stream()
                .sorted(Comparator.comparingInt(h -> -h.getReservas().size()))
                .collect(Collectors.toList());
    }
}