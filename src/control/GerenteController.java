package control;

import model.Gerente;

public class GerenteController {
    public GerenteController(Gerente gerente) {
        System.out.println("GerenteController iniciado para: " + gerente.getNombre());
        // позже добавим GUI
    }
}