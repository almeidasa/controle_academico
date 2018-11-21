package controller;

import DAO.PermissaoDAO;
import entities.Permissao;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @Autor Winder Rezende
 * @Data 15/11/2018, 15:31:40
 */
@ManagedBean
@ViewScoped
public class PermissaoBean {

    private String nomeNovaPerm;
    private String nomePermAnterior;
    private String nome;
    private boolean admin;
    private boolean diret;
    private boolean coord;
    private boolean func;
    private boolean aluno;
    private boolean editar;
    PermissaoDAO perm;
    private ArrayList<Permissao> permissoes;

    public PermissaoBean() {
        perm = new PermissaoDAO();
        permissoes = new ArrayList<>();
        obter();
    }

    public void add() {
        perm.inserirPermissao(nomeNovaPerm);
        obter();
    }
    
    public String cancelar(){
        nomeNovaPerm = null;
        editar = false;
        return ("administracao");
    }
    
    public void iniciaEditar(Permissao p) {
        nomePermAnterior = p.getNome();
        editar = true;
        nomeNovaPerm = p.getNome();
    }
    
    public void alterar(){
        perm.alterarPermissao(nomeNovaPerm, nomePermAnterior);
        editar = false;
        obter();
        cancelar();
    }
    
    public void alterarPermAdmin(Permissao p) {
        perm.alterarPermissaoUsr("admin", p.isAdmin(), p.getNome());
    }
    
    public void alterarPermDiret(Permissao p) {
        perm.alterarPermissaoUsr("diret", p.isDiret(), p.getNome());
    }
    
    public void alterarPermCoord(Permissao p) {
        perm.alterarPermissaoUsr("coord", p.isCoord(), p.getNome());
    }
    
    public void alterarPermFunc(Permissao p) {
        perm.alterarPermissaoUsr("func", p.isFunc(), p.getNome());
    }
    
    public void alterarPermAluno(Permissao p) {
        perm.alterarPermissaoUsr("aluno", p.isAluno(), p.getNome());
    }
    
    public void remover(Permissao p) {
        perm.apagarPermissao(p.getNome());
        obter();
    }

    private void obter() {
        permissoes = perm.obterPermissoes();
    }

    //Getter e Setter
    public String getNomeNovaPerm() {
        return nomeNovaPerm;
    }

    public void setNomeNovaPerm(String nomeNovaPerm) {
        this.nomeNovaPerm = nomeNovaPerm;
    }

    public String getNomePermAnterior() {
        return nomePermAnterior;
    }

    public void setNomePermAnterior(String nomePermAnterior) {
        this.nomePermAnterior = nomePermAnterior;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isDiret() {
        return diret;
    }

    public void setDiret(boolean diret) {
        this.diret = diret;
    }

    public boolean isCoord() {
        return coord;
    }

    public void setCoord(boolean coord) {
        this.coord = coord;
    }

    public boolean isFunc() {
        return func;
    }

    public void setFunc(boolean func) {
        this.func = func;
    }

    public boolean isAluno() {
        return aluno;
    }

    public void setAluno(boolean aluno) {
        this.aluno = aluno;
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    public PermissaoDAO getPerm() {
        return perm;
    }

    public void setPerm(PermissaoDAO perm) {
        this.perm = perm;
    }

    public ArrayList<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(ArrayList<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
}
