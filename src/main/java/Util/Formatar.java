package Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Autor m159255
 * @Data 08/10/2018
 */
public class Formatar {

    public static String Data(String data, String formatoEntrada, String formatoSaida) {
        String dataForm = "";
        try {
            Date date = new SimpleDateFormat(formatoEntrada).parse(data);
            dataForm = new SimpleDateFormat(formatoSaida).format(date);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return dataForm;
    }
    
    public static String data(Date date, String formatoSaida) {
        String dataForm = "";
        try {
            dataForm = new SimpleDateFormat(formatoSaida).format(date);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return dataForm;
    }
}
