package controller;

import DAO.PermissaoDAO;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @Autor Winder Rezende
 * @Data 15/11/2018, 15:31:40
 */
@ManagedBean
@SessionScoped
public class PermissaoBean {

    private String nomeNovaPerm;
    private String nome;
    private boolean admin;
    private boolean diret;
    private boolean coord;
    private boolean func;
    private boolean aluno;
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
    }

    public void teste(PermissaoBean p) {
        System.out.println("admin" + ": " + p.nome + " - " + p.admin);
        if (p.isAdmin()) {
            //perm.alterarPermissao("admin", p.admin, p.nome);
            System.out.println("admin" + ": " + p.nome + " - " + p.admin);
        }
        else if(p.isDiret()){
            //perm.alterarPermissao("diret", p.admin, p.nome);
            System.out.println("diret" + ": " + p.nome + " - " + p.admin);
        }
        else if(p.isCoord()){
            //perm.alterarPermissao("coord", p.admin, p.nome);
            System.out.println("coord" + ": " + p.nome + " - " + p.admin);
        }
        else if(p.isFunc()){
            //perm.alterarPermissao("func", p.admin, p.nome);
            System.out.println("prof" + ": " + p.nome + " - " + p.admin);
        }
        else if(p.isAluno()){
            //perm.alterarPermissao("aluno", p.admin, p.nome);
            System.out.println("func" + ": " + p.nome + " - " + p.admin);
        }
    }
    
    public void alterarPermAdmin() {
        perm.inserirPermissao(nomeNovaPerm);
    }
    
    public void alterarPermDiret() {
        perm.inserirPermissao(nomeNovaPerm);
    }
    
    public void alterarPermCoord() {
        perm.inserirPermissao(nomeNovaPerm);
    }
    
    public void alterarPermFunc() {
        perm.inserirPermissao(nomeNovaPerm);
    }
    
    public void alterarPermAluno() {
        perm.inserirPermissao(nomeNovaPerm);
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
}
