package control;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    LocalDate fechaInicio;
    LocalDate fechaFin;
    boolean confirmada;
    Huesped huesped;
    Habitacion habitacion;

    public Reserva(){

    }
    public Reserva(LocalDate fechaIni, LocalDate fechaFin, boolean confirmada, Huesped huesped, Habitacion habitacion){
        this.fechaInicio = fechaIni;
        this.fechaFin = fechaFin;
        this.confirmada = confirmada;
        this.huesped = huesped;
        this.habitacion = habitacion;
    }

    //Getters y Setters
    public LocalDate getFechaInicio(){
        return fechaInicio;
    }

    public LocalDate getFechaFin(){
        return fechaFin;
    }

    /*
     * Calcula el precio total de la reserva como
     * (número de noches) × (precio por noche con posible descuento VIP).
     */
    public double calcularPrecio(){
        long noches = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        double precioPorNoche = habitacion.calcularPrecioConDescuento(huesped.socioVIP);
        return noches * precioPorNoche;
    }

    /*
     * Confirma la reserva si no lo estaba y la habitación está libre
     * en el intervalo [fechaInicio, fechaFin), añadiéndola a las listas
     * de reservas de habitación y huésped.
     */
    public void confirmarReserva(){
        if (confirmada) {
            throw new IllegalStateException("La reserva ya esta confirmada.");
        }
        if (!habitacion.estaDisponible(fechaInicio, fechaFin)) {
            throw new IllegalStateException(
                    "Esta habitación ya esta reservada entre las fechas ingresados. No se puede confirmar.");
        }
        confirmada = true;
        habitacion.getReservas().add(this);
        huesped.getReservas().add(this);

    }

    /*
     * Cancela la reserva (si estaba confirmada),
     * marcándola como no confirmada y retirándola
     * de las listas correspondientes.
     */
    public void cancelarReserva(){
        if (!confirmada){
            throw new IllegalStateException("La reserva no esta confirmada y no se puede cancelar.");
        }
        confirmada = false;
        habitacion.getReservas().remove(this);
        huesped.getReservas().remove(this);
    }
}
