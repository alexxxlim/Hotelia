package control;

import java.time.LocalDate;
import java.util.List;

public class Hotel {
    private String nombre;
    List<Habitacion> habitaciones;
    List<Reserva> reservas;

    public Hotel(){

    }
    public Hotel(String nombre, List<Habitacion> habitaciones, List<Reserva> reservas){
        this.nombre = nombre;
        this.habitaciones = habitaciones;
        this.reservas = reservas;
    }

    void agregarHabitacion(Habitacion h){

    }

    void agregarReservas(Reserva r){

    }

    List<Habitacion> buscarDisponible(String tipo, LocalDate ini, LocalDate fin){

    }

    List<Habitacion> habitacionesMasReservadas(){

    }

    List<Reserva> reservasPorFecha(LocalDate dia){

    }

}
