package BetaInc;

import Util.Formatar;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @Autor m159255
 * @Data 08/10/2018
 */
@ManagedBean
@SessionScoped
public class ClienteBean {
    
    private String nome;
    private String dtNacimento;
    private String cpf;
    private String rg;
    private String endereco;
    private double qtdReais;
    private List<ClienteBean> cliente = new ArrayList<>();
    
    public ClienteBean() {
    }

    public ClienteBean(String nome, String dtNacimento, String cpf, String rg, String endereco, double qtdReais) {
        this.nome = nome;
        this.dtNacimento = dtNacimento;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.qtdReais = qtdReais;
    }

    public void add(){
        ClienteBean novo = new ClienteBean(this.nome, Formatar.Data(this.dtNacimento, "yyyy-MM-dd", "dd/MM/yyyy"), this.cpf, this.rg, endereco, qtdReais);
        cliente.add(novo);
        nome = "";
        dtNacimento = null;
        cpf = "";
        rg = "";
        endereco = "";
        qtdReais = 0.0;
    }
    
    public void editar(ClienteBean c){
        nome = c.getNome();
        dtNacimento = Formatar.Data(c.getDtNacimento(), "dd/MM/yyyy", "yyyy-MM-dd");
        cpf = c.getCpf();
        rg = c.getRg();
        endereco = c.getEndereco();
        qtdReais = c.getQtdReais();
        cliente.remove(c);
    }
    
    public void remover(ClienteBean c){
        cliente.remove(c);
    }

    //Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDtNacimento() {
        return dtNacimento;
    }

    public void setDtNacimento(String dtNacimento) {
        this.dtNacimento = dtNacimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getQtdReais() {
        return qtdReais;
    }

    public void setQtdReais(double qtdReais) {
        this.qtdReais = qtdReais;
    }

    public List<ClienteBean> getCliente() {
        return cliente;
    }

    public void setCliente(List<ClienteBean> cliente) {
        this.cliente = cliente;
    }
}
