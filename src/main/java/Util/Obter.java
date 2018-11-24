package Util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * @Autor Winder Rezende
 * @Data  10/11/2018
 */
public class Obter {

    public static String CaminhoArquivo(String nomeArquivo) {
        String CaminhoArq;
        try {
            // obtem percurso para o arquivo
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            CaminhoArq = ec.getRealPath("/");
            if (CaminhoArq.endsWith("\\") == false) {
                CaminhoArq += "\\";
            }
            CaminhoArq += "resources\\img\\" + nomeArquivo;
            System.out.println(CaminhoArq);
        } catch (Exception ex) {
            System.out.println("Erro Construtor de TextFileOnResource:" + ex.getMessage());
            CaminhoArq = null;
        }
        return CaminhoArq;
    }
    
    public static String CaminhoFotoRel(String nomeArquivo) {
        String CaminhoArq;
        try {
            // obtem percurso para o arquivo
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            CaminhoArq = ec.getRealPath("/");
            if (CaminhoArq.endsWith("\\") == false) {
                CaminhoArq += "\\";
            }
            CaminhoArq += "WEB-INF\\classes\\report\\" + nomeArquivo;
            System.out.println(CaminhoArq);
        } catch (Exception ex) {
            System.out.println("Erro Construtor de TextFileOnResource:" + ex.getMessage());
            CaminhoArq = null;
        }
        return CaminhoArq;
    } 
}
