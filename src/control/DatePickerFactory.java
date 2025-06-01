package control;

import org.jdatepicker.impl.*;

import javax.swing.*;
import java.util.Locale;
import java.util.Properties;

public class DatePickerFactory {
    public static JDatePickerImpl createPicker(String label) {
        // ✅ Устанавливаем испанскую локаль
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