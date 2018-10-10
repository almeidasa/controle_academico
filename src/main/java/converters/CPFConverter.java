package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("cpfConverter")
public class CPFConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        String c = arg2.replaceAll("\\D", "");
        if (!"".equals(c)) {
            Long l = Long.parseLong(c);
            return l;
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        String t = String.valueOf((Long) arg2);
        return String.format("%s.%s.%s-%s", t.substring(0, 3), t.substring(3, 6),
                t.substring(6, 9), t.substring(9));
    }

}
