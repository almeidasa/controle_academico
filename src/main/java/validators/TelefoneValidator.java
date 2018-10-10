package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("telefoneValidator")
public class TelefoneValidator implements Validator {

    public void validate(FacesContext fc, UIComponent uic, Object o)
            throws ValidatorException {

        String tel = String.valueOf((Long) o);

        if (tel.length() != 10 && tel.length() != 11) {
            FacesMessage msg = new FacesMessage("Meu fio, coloca esse número certo!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}
