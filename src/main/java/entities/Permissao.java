package entities;

/**
 * @Autor Winder Rezende
 * @Data  21/11/2018
 */
public class Permissao {
    private String nome;
    private boolean admin;
    private boolean diret;
    private boolean coord;
    private boolean func;
    private boolean aluno;

    public Permissao() {
    }

    public Permissao(String nome, boolean admin, boolean diret, boolean coord, boolean func, boolean aluno) {
        this.nome = nome;
        this.admin = admin;
        this.diret = diret;
        this.coord = coord;
        this.func = func;
        this.aluno = aluno;
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
}
