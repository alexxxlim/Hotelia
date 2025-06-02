package control;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jdatepicker.JDatePicker;

/**
 * Clase que define el formato de fecha utilizado en los componentes de entrada de tipo fecha
 * dentro de la interfaz gráfica Swing.
 * <p>
 * Utiliza el formato <strong>"dd.MM.yyyy"</strong> para convertir entre
 * objetos {@link String} y {@link Calendar}.
 * <p>
 * Esta clase se utiliza comúnmente con componentes como {@link JDatePicker}
 * que requieren un formateador personalizado.
 *
 * @author
 * @version 1.0
 */
public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

    /**
     * Formateador de fecha con el patrón "dd.MM.yyyy".
     */
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");

    /**
     * Convierte una cadena en un objeto {@link Date}.
     *
     * @param text Cadena con la fecha en formato "dd.MM.yyyy".
     * @return Objeto {@link Date} correspondiente.
     * @throws ParseException Si el texto no se puede interpretar como fecha válida.
     */
    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parse(text);
    }

    /**
     * Convierte un objeto {@link Calendar} en una cadena de texto con el formato definido.
     *
     * @param value Objeto de tipo {@link Calendar}.
     * @return Representación en cadena de la fecha o cadena vacía si el valor es nulo.
     */
    @Override
    public String valueToString(Object value) {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }
        return "";
    }
}