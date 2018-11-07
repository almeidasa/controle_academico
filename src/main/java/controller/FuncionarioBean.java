package controller;

import DAO.FuncionarioDAO;
import entities.Funcionario;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @Autor Winder Rezende
 * @Data 04/11/2018, 23:41:56
 */
@ManagedBean
@RequestScoped
public class FuncionarioBean {

    private int id_user;
    private String nome;
    private String cargo;
    private String email;
    private String telefone;
    private int fk_Usuarios_id_user;
    private ArrayList<Funcionario> funcionario = new ArrayList<>();

    public FuncionarioBean() {
        obter();
    }

    private void obter() {
       FuncionarioDAO func = new FuncionarioDAO();
        funcionario = func.obterFuncionarios();
    }

    public void add() {
        FuncionarioDAO func = new FuncionarioDAO();
        Funcionario fc = new Funcionario(nome, cargo, email, telefone, fk_Usuarios_id_user);
        func.inserirFuncionario(fc);
        obter();
    }

    public void editar() {

    }

    public void remover() {

    }

    //Getters e Seters
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getFk_Usuarios_id_user() {
        return fk_Usuarios_id_user;
    }

    public void setFk_Usuarios_id_user(int fk_Usuarios_id_user) {
        this.fk_Usuarios_id_user = fk_Usuarios_id_user;
    }

    public ArrayList<Funcionario> getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(ArrayList<Funcionario> funcionario) {
        this.funcionario = funcionario;
    }

}
