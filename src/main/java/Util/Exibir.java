package Util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @Autor Winder Rezende
 * @Data  29/10/2018
 */
public class Exibir {

    public static void Mensagem(String mensagem){
        FacesMessage fm = new FacesMessage(mensagem);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
}
