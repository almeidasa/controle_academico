package controller;

import DAO.UsuariosDAO;
import entities.Usuarios;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @Autor Winder Rezende
 * @Data  04/11/2018, 23:41:56
 */
@ManagedBean
@RequestScoped
public class UsuarioBean {

    private int id_user;
    private String login;
    private String senha;
    private String tipo;
    private String situacao;
    private ArrayList<Usuarios> usuarios = new ArrayList<>();
    
    public UsuarioBean() {
        obter();
    }
    
    private void obter(){
        UsuariosDAO us = new UsuariosDAO();
        usuarios = us.obterUsuarios();
    }
    
    public void add(){
        UsuariosDAO us = new UsuariosDAO();
        Usuarios usr = new Usuarios(login, senha, tipo, situacao);
        us.inserirUsuario(usr);
        obter();
    }

    //Getters e Seters
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public ArrayList<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }
}
