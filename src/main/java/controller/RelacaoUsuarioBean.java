package controller;

import DAO.UsuariosDAO;
import Util.Relatorio;
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
public class RelacaoUsuarioBean {

    private String tipo = "";
    private String situacao = "";
    private ArrayList<Usuarios> usuarios;
    Usuarios usr;
    UsuariosDAO usrDao;
    private boolean editar;
    
    public RelacaoUsuarioBean() {
        usuarios = new ArrayList<>();
        usr = new Usuarios();
        usrDao = new UsuariosDAO();
        obter();
    }
    
    private void obter(){
        usuarios = usrDao.obterUsuariosRel(situacao, tipo);
    }
    
    public void filtrar(){
        obter();
    }
    
    public void gerarRelatorio(){
        Relatorio gerar = new Relatorio();
        gerar.getRelatorio(usuarios);
    }

    //Getters e Seters
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
