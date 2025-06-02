package control;

import model.Recepcionista;

/**
 * Controlador asociado al rol de {@link Recepcionista} dentro del sistema.
 *
 * Esta clase se encarga de gestionar la lógica y futuras vistas que estarán
 * disponibles para los usuarios con rol de recepcionista.
 *
 * Actualmente sirve como punto de partida para ampliar la funcionalidad relacionada
 * con la atención al cliente, gestión de reservas y soporte al gerente.
 *
 * @author
 * @version 1.0
 */
public class RecepcionistaController {

    /**
     * Constructor que inicializa el controlador para un recepcionista autenticado.
     *
     * @param recepcionista Objeto de tipo {@link Recepcionista} correspondiente al usuario activo.
     */
    public RecepcionistaController(Recepcionista recepcionista) {
        System.out.println("RecepcionistaController iniciado para: " + recepcionista.getNombre());
        // Vista e interfaz gráfica se implementarán en el futuro
    }
}