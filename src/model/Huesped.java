package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un huésped del sistema Hotelia.
 * <p>
 * El huésped puede gestionar reservas, dejar reseñas, pagar membresías VIP,
 * y acceder a su historial de estancias. Puede estar asociado a una habitación
 * actual y tener múltiples reservas anteriores o activas.
 * <p>
 * Hereda de {@link Usuario}.
 *
 * @author
 * @version 1.0
 */
public class Huesped extends Usuario {

    private int idVIP;
    private boolean esSocioVIP;
    private Habitacion habitacion;
    private ArrayList<Reserva> reservas = new ArrayList<>();

    /**
     * Constructor básico de huésped.
     *
     * @param id          Identificador único.
     * @param idVIP       ID de membresía VIP (si aplica).
     * @param nombre      Nombre del usuario.
     * @param correo      Correo electrónico.
     * @param contrasenia Contraseña de acceso.
     * @param esSocioVIP  Indica si el huésped es socio VIP.
     */
    public Huesped(int id, int idVIP, String nombre, String correo, String contrasenia, boolean esSocioVIP) {
        super(id, nombre, correo, contrasenia);
        this.idVIP = idVIP;
        this.esSocioVIP = esSocioVIP;
    }

    /**
     * Constructor completo con habitación actual y reservas.
     *
     * @param id          Identificador único.
     * @param nombre      Nombre del huésped.
     * @param correo      Correo electrónico.
     * @param contrasenia Contraseña de acceso.
     * @param idVIP       ID de socio VIP.
     * @param esSocioVIP  Indica si es socio VIP.
     * @param habitacion  Habitación actual asociada (puede ser null).
     * @param reservas    Lista de reservas previas (puede ser null).
     */
    public Huesped(int id, String nombre, String correo, String contrasenia, int idVIP, boolean esSocioVIP, Habitacion habitacion, ArrayList<Reserva> reservas) {
        super(id, nombre, correo, contrasenia);
        this.idVIP = idVIP;
        this.esSocioVIP = esSocioVIP;
        this.habitacion = habitacion;
        this.reservas = reservas != null ? reservas : new ArrayList<>();
    }

    // ========================
    // Getters y Setters
    // ========================

    public int getIdVIP() {
        return idVIP;
    }

    public void setIdVIP(int idVIP) {
        this.idVIP = idVIP;
    }

    public boolean isEsSocioVIP() {
        return esSocioVIP;
    }

    public void setEsSocioVIP(boolean esSocioVIP) {
        this.esSocioVIP = esSocioVIP;
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
    // Funciones principales
    // ========================

    /**
     * Intenta crear una nueva reserva para una habitación específica.
     *
     * @param h      Habitación deseada.
     * @param inicio Fecha de inicio de la reserva.
     * @param fin    Fecha de fin de la reserva.
     * @return Objeto {@link Reserva} creado.
     * @throws IllegalStateException si la habitación no está disponible.
     */
    public Reserva hacerReserva(Habitacion h, LocalDate inicio, LocalDate fin) {
        if (!h.estaDisponible(inicio, fin)) {
            throw new IllegalStateException("La habitación no está disponible para las fechas indicadas.");
        }
        int dias = (int) (fin.toEpochDay() - inicio.toEpochDay());
        Reserva r = new Reserva(-1, inicio, fin, dias, false, this, h); // ID asignado posteriormente
        r.confirmarReserva();
        return r;
    }

    /**
     * Cancela una reserva existente.
     *
     * @param r Reserva que se desea cancelar.
     */
    public void cancelarReserva(Reserva r) {
        r.cancelarReserva();
    }

    /**
     * Permite al huésped dejar una reseña sobre una habitación.
     *
     * @param h          Habitación evaluada.
     * @param comentario Comentario del huésped.
     * @param rating     Valoración numérica (1 a 5).
     */
    public void dejarResenia(Habitacion h, String comentario, int rating) {
        Resenia nueva = new Resenia(comentario, rating, this);
        h.agregarResenia(nueva);
    }

    /**
     * Marca al huésped como socio VIP.
     */
    public void pagarMembresiaVIP() {
        this.esSocioVIP = true;
        System.out.println("¡Felicidades! Ahora eres socio VIP.");
    }

    /**
     * Devuelve una lista con todas las habitaciones en las que el huésped ha estado.
     *
     * @return Lista de habitaciones reservadas previamente.
     */
    public List<Habitacion> verHistorialReservas() {
        List<Habitacion> historial = new ArrayList<>();
        for (Reserva r : reservas) {
            historial.add(r.getHabitacion());
        }
        return historial;
    }
}