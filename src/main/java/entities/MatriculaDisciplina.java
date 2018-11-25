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

    private int id;
    private String conceito;
    private String semestre;
    private int ano;
    private String situacao;
    private String fk_Disciplina_codigo;
    private String fk_Aluno_cpf;
    private String nome_aluno;
    private String nome_disciplina;
    private String nome_curso;
    
    public MatriculaDisciplina(String conceito, String semestre, int ano, String situacao, String fk_Disciplina_codigo, String fk_Aluno_cpf) {
        this.conceito = conceito;
        this.semestre = semestre;
        this.ano = ano;
        this.situacao = situacao;
        this.fk_Disciplina_codigo = fk_Disciplina_codigo;
        this.fk_Aluno_cpf = fk_Aluno_cpf;
    }

    public MatriculaDisciplina(int id, String conceito, String semestre, int ano, String situacao, String fk_Disciplina_codigo, String fk_Aluno_cpf) {
        this.id = id;
        this.conceito = conceito;
        this.semestre = semestre;
        this.ano = ano;
        this.situacao = situacao;
        this.fk_Disciplina_codigo = fk_Disciplina_codigo;
        this.fk_Aluno_cpf = fk_Aluno_cpf;
    }

    public MatriculaDisciplina(int id, String conceito, String semestre, int ano, String situacao, String fk_Disciplina_codigo, String fk_Aluno_cpf, String nome_aluno, String nome_disciplina, String nome_curso) {
        this.id = id;
        this.conceito = conceito;
        this.semestre = semestre;
        this.ano = ano;
        this.situacao = situacao;
        this.fk_Disciplina_codigo = fk_Disciplina_codigo;
        this.fk_Aluno_cpf = fk_Aluno_cpf;
        this.nome_aluno = nome_aluno;
        this.nome_disciplina = nome_disciplina;
        this.nome_curso = nome_curso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNome_aluno() {
        return nome_aluno;
    }

    public void setNome_aluno(String nome_aluno) {
        this.nome_aluno = nome_aluno;
    }

    public String getNome_disciplina() {
        return nome_disciplina;
    }

    public void setNome_disciplina(String nome_disciplina) {
        this.nome_disciplina = nome_disciplina;
    }

    public String getNome_curso() {
        return nome_curso;
    }

    public void setNome_curso(String nome_curso) {
        this.nome_curso = nome_curso;
    }
}
