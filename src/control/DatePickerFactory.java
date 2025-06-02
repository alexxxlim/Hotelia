package control;

import org.jdatepicker.impl.*;

import javax.swing.*;
import java.util.Locale;
import java.util.Properties;

/**
 * Clase de utilidad para crear componentes de selección de fecha con configuración local en español.
 *
 * Esta clase configura el {@link JDatePickerImpl} con etiquetas en español y un formateador personalizado
 * que utiliza el formato "dd.MM.yyyy".
 *
 * @author
 * @version 1.0
 */
public class DatePickerFactory {

    /**
     * Crea un componente {@link JDatePickerImpl} con configuración en idioma español.
     *
     * @param label Etiqueta (no utilizada directamente en este método, reservada para posibles extensiones).
     * @return Componente {@link JDatePickerImpl} listo para ser insertado en una interfaz gráfica.
     */
    public static JDatePickerImpl createPicker(String label) {
        Locale.setDefault(new Locale("es", "ES"));

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Hoy");
        p.put("text.month", "Mes");
        p.put("text.year", "Año");

        JDatePanelImpl panel = new JDatePanelImpl(model, p);
        return new JDatePickerImpl(panel, new DateLabelFormatter());
    }
}