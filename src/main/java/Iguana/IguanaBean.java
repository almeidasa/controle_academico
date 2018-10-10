package Iguana;

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
public class IguanaBean {
    private String nome;
    private int idade;
    private List<IguanaBean> iguana = new ArrayList<>();
    
    public IguanaBean(){
    }

    public IguanaBean(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String addIguana(){
        IguanaBean novo = new IguanaBean(this.nome, this.idade);
        iguana.add(novo);
        nome = "";
        idade = 0;
        return "iguanalista";
    }
    
    public void removeCadastrado(IguanaBean c){
        iguana.remove(c);
    }
    
    public String editarCadadatarado(IguanaBean c){
        nome = c.getNome();
        idade = c.getIdade();
        iguana.remove(c);
        return "iguanacad";
    }
    
    //Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public List<IguanaBean> getIguana() {
        return iguana;
    }

    public void setIguana(List<IguanaBean> iguana) {
        this.iguana = iguana;
    }
}
