package converters;

import java.util.ArrayList;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("arrayStringConverter")
public class StringArrayConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String s) {
        ArrayList<String> arrListString = new ArrayList<>();
        String[] arrString = s.split(",");

        for (String sFail : arrString) {
            arrListString.add(sFail.trim());
        }

        return arrListString;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        ArrayList<String> arrListString = (ArrayList<String>) o;
        String tudoJunto = String.join(", ", arrListString);

        return tudoJunto;
    }

}
