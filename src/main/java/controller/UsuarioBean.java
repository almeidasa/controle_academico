package controller;

import DAO.UsuariosDAO;
import entities.Usuarios;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @Autor Winder Rezende
 * @Data  04/11/2018, 23:41:56
 */
@ManagedBean
@ViewScoped
public class UsuarioBean {

    private int id_user;
    private String login;
    private String senha;
    private String tipo;
    private String situacao;
    private ArrayList<Usuarios> usuarios;
    Usuarios usr;
    UsuariosDAO usrDao;
    private boolean editar;
    
    public UsuarioBean() {
        usuarios = new ArrayList<>();
        usr = new Usuarios();
        usrDao = new UsuariosDAO();
        obter();
    }
    
    public String cancelar(){
        login = null;
        senha = "senha";
        tipo = "";
        situacao = "true";
        editar = false;
        return ("cadastrarUsuario");
    }
    
    private void obter(){
        usuarios = usrDao.obterUsuarios();
    }
    
    public void add(){
        usr = new Usuarios(login, senha, tipo, situacao);
        usrDao.inserirUsuario(usr);
        obter();
        cancelar();
    }
    
    public void iniciaEditar(Usuarios lista) {
        editar = true;
        id_user = lista.getId_user();
        login = lista.getLogin();
        senha = lista.getSenha().equals("Privada") ? "" : "senha" ;
        tipo = lista.getTipo();
        situacao = lista.getSituacao().equals("Ativo") ? "true" : "false";
    }
    
    public void alterar(){
        usr = new Usuarios(id_user, login, senha, tipo, situacao);
        usrDao.alterarUsuario(usr);
        editar = false;
        obter();
        cancelar();
    }

    public void remover(Usuarios lista) {
        usrDao.apagarUsuario(lista.getId_user());
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

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }
}
