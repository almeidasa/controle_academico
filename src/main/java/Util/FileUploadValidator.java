package Util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

/**
 * @Autor Winder Rezende
 * @Data 11/11/2018
 */
@FacesValidator(value = "fileUploadValidator")
public class FileUploadValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Part file = (Part) value;

        FacesMessage message = null;

        try {

            if (file == null || file.getSize() <= 0 || file.getContentType().isEmpty()) {
                message = new FacesMessage("O arquivo selecionado é invalido!");
            } else if (!file.getContentType().endsWith("png") && !file.getContentType().endsWith("jpeg") && !file.getContentType().endsWith("jpg")) {
                message = new FacesMessage("São aceitos apenas arquivos do tipo: png ou jpg.");
            } else if (file.getSize() > 524288) {
                message = new FacesMessage("Arquivo muito grande. O tamanho máximo permitido é 500KB.");
            }

            if (message != null && !message.getDetail().isEmpty()) {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }
        } catch (Exception ex) {
            throw new ValidatorException(new FacesMessage(ex.getMessage()));
        }
    }
}
