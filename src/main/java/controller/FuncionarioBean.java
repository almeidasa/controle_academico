package controller;

import DAO.FuncionarioDAO;
import DAO.UsuariosDAO;
import entities.Funcionario;
import entities.Usuarios;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
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
    private ArrayList<Usuarios> user = new ArrayList<>();
    private Map<Integer, String> ItensBoxUsuarios;

    public FuncionarioBean() {
        obter();
        setBoxUsuarios();
    }

    private void obter() {
        FuncionarioDAO func = new FuncionarioDAO();
        funcionario = func.obterFuncionarios();
    }

    public void limpaTela(){
        nome = "";
        cargo = "";
        email = "";
        telefone = "";
        fk_Usuarios_id_user = 0;
    }
        
    public void add() {
        FuncionarioDAO func = new FuncionarioDAO();
        Funcionario fc = new Funcionario(nome, cargo, email, telefone, fk_Usuarios_id_user);
        func.inserirFuncionario(fc);
        limpaTela();
        obter();
    }

    public void editar() {

    }

    public void remover(Funcionario func) {
        
    }

    private void setBoxUsuarios() {
        ItensBoxUsuarios = new LinkedHashMap<>();
        UsuariosDAO us = new UsuariosDAO();
        user = us.obterUsuarios();

        for (Usuarios usuario : user) {
            ItensBoxUsuarios.put(0, "Selecione um Usuário");
            ItensBoxUsuarios.put(usuario.getId_user(), usuario.getLogin());
        }
    }

    public ArrayList<Usuarios> getUser() {
        return user;
    }

    public void setUser(ArrayList<Usuarios> user) {
        this.user = user;
    }

    public Map<Integer, String> getItensBoxUsuarios() {
        return ItensBoxUsuarios;
    }

    public void setItensBoxUsuarios(Map<Integer, String> ItensBoxUsuarios) {
        this.ItensBoxUsuarios = ItensBoxUsuarios;
    }

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
