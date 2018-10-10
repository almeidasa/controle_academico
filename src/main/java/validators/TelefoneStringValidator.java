package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("telefoneStringValidator")
public class TelefoneStringValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o)
            throws ValidatorException {

        String ts = String.valueOf(o);

        if (!ts.matches("\\(\\d{2}\\) \\d{4,5}-\\d{4}")) {
            FacesMessage msg = new FacesMessage("Meu fio, coloca esse número certo!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }

}
