package controller;

import DAO.PermissaoDAO;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
    private ArrayList<PermissaoBean> permissoes;
    private Map<String, Boolean> permissao;

    public PermissaoBean() {
        perm = new PermissaoDAO();
        permissoes = new ArrayList<>();
        permissao = new LinkedHashMap<>();
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
    
    public void iniciaEditar(PermissaoBean p) {
        nomePermAnterior = p.nome;
        editar = true;
        nomeNovaPerm = p.nome;
    }
    
    public void alterar(){
        perm.alterarPermissao(nomeNovaPerm, nomePermAnterior);
        editar = false;
        obter();
        cancelar();
    }
    
    public void remover(PermissaoBean p) {
        perm.apagarPermissao(p.nome);
        obter();
    }
    
    public void alterarPermAdmin(PermissaoBean p) {
        perm.alterarPermissaoUsr("admin", p.admin, p.nome);
    }
    
    public void alterarPermDiret(PermissaoBean p) {
        perm.alterarPermissaoUsr("diret", p.diret, p.nome);
    }
    
    public void alterarPermCoord(PermissaoBean p) {
        perm.alterarPermissaoUsr("coord", p.coord, p.nome);
    }
    
    public void alterarPermFunc(PermissaoBean p) {
        perm.alterarPermissaoUsr("func", p.func, p.nome);
    }
    
    public void alterarPermAluno(PermissaoBean p) {
        perm.alterarPermissaoUsr("aluno", p.aluno, p.nome);
    }

    public PermissaoBean(String nome, boolean admin, boolean diret, boolean coord, boolean func, boolean aluno) {
        this.nome = nome;
        this.admin = admin;
        this.diret = diret;
        this.coord = coord;
        this.func = func;
        this.aluno = aluno;
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

    public PermissaoDAO getPerm() {
        return perm;
    }

    public void setPerm(PermissaoDAO perm) {
        this.perm = perm;
    }

    public ArrayList<PermissaoBean> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(ArrayList<PermissaoBean> permissoes) {
        this.permissoes = permissoes;
    }

    public Map<String, Boolean> getPermissao() {
        return permissao;
    }

    public void setPermissao(Map<String, Boolean> permissao) {
        this.permissao = permissao;
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    public String getNomePermAnterior() {
        return nomePermAnterior;
    }

    public void setNomePermAnterior(String nomePermAnterior) {
        this.nomePermAnterior = nomePermAnterior;
    }
}
