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
 * @Autor Alexandre Almeida
 * @Data 07/11/2018
 */
@ManagedBean
@RequestScoped
public class FuncionarioBean {

    private int id;
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

    public void limpaTela() {
        id = 0;
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

    public void editar(Funcionario f) {
        id = f.getId();
        nome = f.getNome();
        cargo = f.getCargo();
        email = f.getEmail();
        telefone = f.getTelefone();
        fk_Usuarios_id_user = f.getFk_Usuarios_id_user();
        remover(f);
    }

    public void remover(Funcionario f) {
        funcionario.remove(f);
        FuncionarioDAO func = new FuncionarioDAO();
        func.removerFuncionario(f);
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

    public int getId() {
        return id;
    }

    public void setId(int id_user) {
        this.id = id_user;
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
