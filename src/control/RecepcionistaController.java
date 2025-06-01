package control;

import model.Recepcionista;

public class RecepcionistaController {
    public RecepcionistaController(Recepcionista recepcionista) {
        System.out.println("RecepcionistaController iniciado для: " + recepcionista.getNombre());
        // позже добавим GUI
    }
}