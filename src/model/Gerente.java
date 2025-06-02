package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que representa al usuario con rol de Gerente dentro del sistema Hotelia.
 * <p>
 * El gerente tiene la capacidad de gestionar habitaciones (agregar, eliminar, ver todas),
 * así como acceder a métricas como ingresos y valoraciones promedio por habitación.
 * <p>
 * También puede asignar permisos a recepcionistas.
 * <p>
 * Extiende de la clase base {@link Usuario}.
 *
 * @author
 * @version 1.0
 */
public class Gerente extends Usuario {

    /**
     * Lista de habitaciones bajo la gestión de este gerente.
     */
    private ArrayList<Habitacion> habitaciones;

    /**
     * Constructor del gerente.
     *
     * @param id          ID único del usuario.
     * @param nombre      Nombre del gerente.
     * @param correo      Correo electrónico del gerente.
     * @param contrasenia Contraseña de acceso.
     */
    public Gerente(int id, String nombre, String correo, String contrasenia) {
        super(id, nombre, correo, contrasenia);
        this.habitaciones = new ArrayList<>();
    }

    /**
     * Devuelve la lista de habitaciones asignadas al gerente.
     *
     * @return Lista de objetos {@link Habitacion}.
     */
    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    /**
     * Establece la lista completa de habitaciones gestionadas.
     *
     * @param habitaciones Lista de habitaciones a asignar.
     */
    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    /**
     * Devuelve todas las habitaciones gestionadas por el gerente.
     *
     * @return Lista de objetos {@link Habitacion}.
     */
    public List<Habitacion> verTodasLasHabitaciones() {
        return habitaciones;
    }

    /**
     * Agrega una nueva habitación a la lista gestionada.
     *
     * @param h Objeto {@link Habitacion} a agregar.
     */
    public void agregarHabitacion(Habitacion h) {
        habitaciones.add(h);
    }

    /**
     * Elimina una habitación identificada por su número.
     *
     * @param numero Número de la habitación a eliminar.
     */
    public void eliminarHabitacion(int numero) {
        habitaciones.removeIf(h -> h.getNumero() == numero);
    }

    /**
     * Calcula los ingresos totales obtenidos por cada habitación.
     *
     * @return Mapa donde la clave es una habitación y el valor es el ingreso total generado.
     */
    public Map<Habitacion, Double> obtenerIngresosPorHabitacion() {
        Map<Habitacion, Double> ingresos = new HashMap<>();
        for (Habitacion h : habitaciones) {
            double total = 0.0;
            for (Reserva r : h.getReservas()) {
                total += r.calcularPrecioTotal();
            }
            ingresos.put(h, total);
        }
        return ingresos;
    }

    /**
     * Devuelve el promedio de rating de cada habitación gestionada.
     *
     * @return Mapa donde la clave es una habitación y el valor es su rating promedio.
     */
    public Map<Habitacion, Double> obtenerPromedioRating() {
        Map<Habitacion, Double> ratings = new HashMap<>();
        for (Habitacion h : habitaciones) {
            ratings.put(h, h.obtenerRatingPromedio());
        }
        return ratings;
    }

    /**
     * Asigna permisos a un recepcionista.
     *
     * <p>En la versión MVP del sistema se asume que los permisos son automáticos.</p>
     *
     * @param r Objeto {@link Recepcionista} al que se le asignan permisos.
     */
    public void asignarPermisos(Recepcionista r) {
        System.out.println("Permisos asignados a recepcionista: " + r.getNombre());
    }
}