package Cobra;

import Util.Formatar;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @Autor Winder Rezende
 * @Data  23/09/2018, 00:06:18
 */
@ManagedBean
@SessionScoped
public class CobraBean {
    private String nome;
    private String dtCaptura;
    private String tamanho;
    private String peso;
    private List<CobraBean> cobra = new ArrayList<>();
    
    public CobraBean(){
    }

    public CobraBean(String nome, String dtCaptura, String tamanho, String peso) {
        this.nome = nome;
        this.dtCaptura = dtCaptura;
        this.tamanho = tamanho;
        this.peso = peso;
    }

    public String add(){
        CobraBean novo = new CobraBean(this.nome, Formatar.Data(this.dtCaptura, "yyyy-MM-dd", "dd/MM/yyyy"), this.tamanho, this.peso);
        cobra.add(novo);
        nome = "";
        dtCaptura = "";
        tamanho = "0";
        peso = "0";
        return "cobralista";
    }
    
    public void removeCadastrado(CobraBean c){
        cobra.remove(c);
    }
    
    public String editarCadadatarado(CobraBean c){
        nome = c.getNome();
        dtCaptura = Formatar.Data(c.getDtCaptura(), "dd/MM/yyyy", "yyyy-MM-dd");
        tamanho = c.getTamanho();
        peso = c.getPeso();
        cobra.remove(c);
        return "cobracad";
    }
    
    //Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDtCaptura() {
        return dtCaptura;
    }

    public void setDtCaptura(String dtCaptura) {
        this.dtCaptura = dtCaptura;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public List<CobraBean> getCobra() {
        return cobra;
    }

    public void setCobra(List<CobraBean> cobra) {
        this.cobra = cobra;
    }
}
