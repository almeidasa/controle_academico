package Gato;

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
public class GatoBean {
    private String nome;
    private String raca;
    private int idade;
    private List<GatoBean> gato = new ArrayList<>();
    
    public GatoBean(){
    }

    public GatoBean(String nome, String raca, int idade) {
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
    }

    public void addGato(){
        GatoBean novo = new GatoBean(this.nome, this.raca, this.idade);
        gato.add(novo);
        nome = "";
        raca = "";
        idade = 0;
    }
    
    public String listarGato(){
        return "gatolista";
    }
    
    public void removeCadastrado(GatoBean c){
        gato.remove(c);
    }
    
    public String editarCadadatarado(GatoBean c){
        nome = c.getNome();
        raca = c.getRaca();
        idade = c.getIdade();
        gato.remove(c);
        return "gatocad";
    }
    
    //Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public List<GatoBean> getGato() {
        return gato;
    }

    public void setGato(List<GatoBean> gato) {
        this.gato = gato;
    }
}
