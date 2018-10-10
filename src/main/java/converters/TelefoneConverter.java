package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("telefoneConverter")
public class TelefoneConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String s) {
        String t = s.replaceAll("\\D", "");
        if (t != "") {
            Long l = Long.parseLong(t);
            return l;
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        String t = String.valueOf((Long) o);
        return "(" + t.substring(0, 2) + ") " + t.substring(2, 6) + "-"
                + t.substring(6);
    }

}
