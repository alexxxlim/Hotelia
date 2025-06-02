package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un recepcionista dentro del sistema Hotelia.
 *
 * El recepcionista tiene funciones de apoyo al huésped, como gestionar reservas
 * en nombre de los clientes, buscar disponibilidad y brindar asistencia.
 *
 * Hereda de {@link Usuario}.
 *
 * @author
 * @version 1.0
 */
public class Recepcionista extends Usuario {

    private Huesped huesped;
    private Habitacion habitacion;
    private ArrayList<Reserva> reservas;

    /**
     * Constructor del recepcionista.
     *
     * @param id ID único del usuario.
     * @param nombre Nombre del recepcionista.
     * @param correo Correo electrónico del recepcionista.
     * @param contrasenia Contraseña de acceso.
     */
    public Recepcionista(int id, String nombre, String correo, String contrasenia) {
        super(id, nombre, correo, contrasenia);
        this.reservas = new ArrayList<>();
    }

    // ========================
    // Getters y Setters
    // ========================

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    // ========================
    // Funcionalidad Principal
    // ========================

    /**
     * Devuelve una lista con todas las reservas confirmadas.
     *
     * @return Lista de reservas activas (confirmadas).
     */
    public List<Reserva> verReservasActivas() {
        List<Reserva> activas = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.isEsConfirmada()) {
                activas.add(r);
            }
        }
        return activas;
    }

    /**
     * Asiste a un huésped proporcionando apoyo o información.
     *
     * @param h Objeto {@link Huesped} que requiere asistencia.
     */
    public void asistirCliente(Huesped h) {
        System.out.println("Asistiendo al cliente: " + h.getNombre());
    }

    /**
     * Busca una habitación disponible que cumpla con el tipo y fechas especificadas.
     *
     * @param habitaciones Lista de habitaciones a buscar.
     * @param tipo Tipo de habitación deseado (ej. "VIP").
     * @param ini Fecha de inicio de la estancia.
     * @param fin Fecha de fin de la estancia.
     * @return Habitación disponible o {@code null} si no hay coincidencias.
     */
    public Habitacion buscarHabitacionDisponible(List<Habitacion> habitaciones, String tipo, LocalDate ini, LocalDate fin) {
        for (Habitacion h : habitaciones) {
            if (h.getTipo().equalsIgnoreCase(tipo) && h.estaDisponible(ini, fin)) {
                return h;
            }
        }
        return null;
    }

    /**
     * Crea una reserva en nombre de un huésped, si la habitación está disponible.
     *
     * @param h Huésped para quien se realiza la reserva.
     * @param hab Habitación a reservar.
     * @param ini Fecha de inicio.
     * @param fin Fecha de fin.
     */
    public void crearReservaParaCliente(Huesped h, Habitacion hab, LocalDate ini, LocalDate fin) {
        if (!hab.estaDisponible(ini, fin)) {
            System.out.println("La habitación no está disponible para las fechas indicadas.");
            return;
        }
        int dias = (int) (fin.toEpochDay() - ini.toEpochDay());
        Reserva r = new Reserva(-1, ini, fin, dias, false, h, hab);
        r.confirmarReserva();
        reservas.add(r);
        System.out.println("Reserva creada y confirmada para: " + h.getNombre());
    }
}