/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Alexandre Almeida
 */
public class MatriculaDisciplina {

    private String conceito;
    private String semestre;
    private int ano;
    private String situacao;
    private String fk_Disciplina_codigo;
    private String fk_Aluno_cpf;

    public MatriculaDisciplina(String conceito, String semestre, int ano, String situacao, String fk_Disciplina_codigo, String fk_Aluno_cpf) {
        this.conceito = conceito;
        this.semestre = semestre;
        this.ano = ano;
        this.situacao = situacao;
        this.fk_Disciplina_codigo = fk_Disciplina_codigo;
        this.fk_Aluno_cpf = fk_Aluno_cpf;
    }

    public MatriculaDisciplina() {
    }

    public String getConceito() {
        return conceito;
    }

    public void setConceito(String conceito) {
        this.conceito = conceito;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getFk_Disciplina_codigo() {
        return fk_Disciplina_codigo;
    }

    public void setFk_Disciplina_codigo(String fk_Disciplina_codigo) {
        this.fk_Disciplina_codigo = fk_Disciplina_codigo;
    }

    public String getFk_Aluno_cpf() {
        return fk_Aluno_cpf;
    }

    public void setFk_Aluno_cpf(String fk_Aluno_cpf) {
        this.fk_Aluno_cpf = fk_Aluno_cpf;
    }

}
