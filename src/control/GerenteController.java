package control;

import model.Gerente;

/**
 * Controlador asociado al rol {@link Gerente} dentro del sistema.
 *
 * Esta clase se encarga de gestionar la lógica y vista asociada al gerente.
 * Actualmente, su funcionalidad es limitada y sirve como punto de entrada para futuras ampliaciones,
 * como la integración de interfaces gráficas específicas para este tipo de usuario.
 *
 * @author
 * @version 1.0
 */
public class GerenteController {

    /**
     * Constructor que inicializa el controlador con un objeto {@link Gerente}.
     *
     * @param gerente Objeto de tipo {@link Gerente} que representa al usuario autenticado.
     */
    public GerenteController(Gerente gerente) {
        System.out.println("GerenteController iniciado para: " + gerente.getNombre());
        // GUI futura será implementada aquí
    }
}