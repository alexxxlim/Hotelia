package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gerente extends Usuario {

    public ArrayList<Habitacion> habitaciones;

    public Gerente(int id, String nombre, String correo, String contrasenia) {
        super(id, nombre, correo, contrasenia);
        this.habitaciones = new ArrayList<>();
    }

    // Funciones Principales:

    public List<Habitacion> verTodasLasHabitaciones() {
        return habitaciones;
    }

    public void agregarHabitacion(Habitacion h) {
        habitaciones.add(h);
    }

    public void eliminarHabitacion(int numero) {
        habitaciones.removeIf(h -> h.numero == numero);
    }

    public Map<Habitacion, Double> obtenerIngresosPorHabitacion() {
        Map<Habitacion, Double> ingresos = new HashMap<>();
        for (Habitacion h : habitaciones) {
            double total = 0.0;
            for (Reserva r : h.reservas) {
                total += r.calcularPrecioTotal();
            }
            ingresos.put(h, total);
        }
        return ingresos;
    }

    public Map<Habitacion, Double> obtenerPromedioRating() {
        Map<Habitacion, Double> ratings = new HashMap<>();
        for (Habitacion h : habitaciones) {
            ratings.put(h, h.obtenerRatingPromedio());
        }
        return ratings;
    }

    public void asignarPermisos(Recepcionista r) {
        // En MVP podemos asumir que todos los recepcionistas tienen permisos por defecto
        System.out.println("Permisos asignados a recepcionista: " + r.nombre);
    }
}