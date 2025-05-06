package model;

import java.util.ArrayList;

public class Gerente extends Usuario {

    public ArrayList<Habitacion> habitaciones;
    //public ArrayList<Permiso> permisos;

    public Gerente(int id, String nombre, String correo, String contrasenia) {
        super(id, nombre, correo, contrasenia);

        this.habitaciones = new ArrayList<>();
    }
}
//TODO: @alexlim: falta funciones





/*
//Funciones Principales:

    List<Habitacion> verTodasLasHabitaciones() {
//TODO:
}

    void agregarHabitacion(Habitacion h) {
//TODO:
}

    void eliminarHabitacion(int numero) {
//TODO:
}

    Map<Habitacion, Double> obtenerIngresosPorHabitacion() {
//TODO:
}

    Map<Habitacion, Double> obtenerPromedioRating() {
//TODO:
}

    void asignarPermisos(Recepcionista r) {
//TODO:
}
*/