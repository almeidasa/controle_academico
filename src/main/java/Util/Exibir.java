package Util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @Autor Winder Rezende
 * @Data  29/10/2018
 */
public class Exibir {

    public static void menssagem(String menssagem){
        FacesMessage mensagem = new FacesMessage(menssagem);
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }
}
