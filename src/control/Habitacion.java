package control;

import java.time.LocalDate;
import java.util.List;

public class Habitacion {
    int numero;
    String tipo;
    double precio;
    boolean disponible;
    List<Reseña> reseñas;
    List<Reserva> reservas;
    boolean mantenimiento;

    public Habitacion(){
    }
    public Habitacion(int numero, String tipo, double precio, boolean disponible, boolean mantenimiento,
                      List<Reseña> reseñas, List<Reserva> reservas){
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
        this.disponible = disponible;
        this.mantenimiento = mantenimiento;
        this.reservas = reservas;
        this.reseñas = reseñas;
    }

    // Getters y setters
    public List<Reserva> getReservas(){
        return reservas;
    }

    public List<Reseña> getReseñas(){
        return reseñas;
    }

    /* Confirma que la habitacion este disponible para una reserva.
    (Que no haya un solapamiento o que este en mantenimiento.)
     */
    boolean estaDisponible(LocalDate ini, LocalDate fin){
        if (!disponible || mantenimiento){
            return false;
        }
        for (Reserva r: reservas){
            boolean solapa = !r.getFechaFin().isBefore(ini) && !r.getFechaInicio().isAfter(fin);
            if (solapa){
                return false;
            }
        }
        return true;
    }

    double calcularPrecioConDescuento(boolean vip){
        final double descuento = 0.20;
        if (vip) {
            return precio * (1 - descuento);
        }
        else {
            return precio;
        }
    }

    double ratingPromedio(){
        if (reseñas == null || reseñas.isEmpty()){
            return 0.0;
        }
        int suma = 0;
        for (Reseña r : reseñas){
            suma += r.getRating();
        }
        return suma / (double) reseñas.size();
    }
}
