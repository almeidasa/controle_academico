package entities;

/**
 * @Autor Winder Rezende
 * @Data  24/11/2018
 */
public class HistoricoAluno {
    
    private String nome_curso;
    private String nome_aluno;
    private String cpf;
    private String matricula;
    private String nome_Disciplina;
    private String ano;
    private String semestre;
    private String conceito;
    private String situacao;
    private String data_inicio;
    private String duracao_curso;

    public HistoricoAluno(String nome_curso, String nome_aluno, String cpf, String matricula, String situacao, String data_inicio, String duracao_curso) {
        this.nome_curso = nome_curso;
        this.nome_aluno = nome_aluno;
        this.cpf = cpf;
        this.matricula = matricula;
        this.situacao = situacao;
        this.data_inicio = data_inicio;
        this.duracao_curso = duracao_curso;
    }

    public HistoricoAluno(String nome_curso, String nome_aluno, String cpf, String matricula, String nome_Disciplina, String ano, String semestre, String conceito, String situacao) {
        this.nome_curso = nome_curso;
        this.nome_aluno = nome_aluno;
        this.cpf = cpf;
        this.matricula = matricula;
        this.nome_Disciplina = nome_Disciplina;
        this.ano = ano;
        this.semestre = semestre;
        this.conceito = conceito;
        this.situacao = situacao;
    }

    public String getNome_curso() {
        return nome_curso;
    }

    public void setNome_curso(String nome_curso) {
        this.nome_curso = nome_curso;
    }

    public String getNome_aluno() {
        return nome_aluno;
    }

    public void setNome_aluno(String nome_aluno) {
        this.nome_aluno = nome_aluno;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome_Disciplina() {
        return nome_Disciplina;
    }

    public void setNome_Disciplina(String nome_Disciplina) {
        this.nome_Disciplina = nome_Disciplina;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getConceito() {
        return conceito;
    }

    public void setConceito(String conceito) {
        this.conceito = conceito;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getDuracao_curso() {
        return duracao_curso;
    }

    public void setDuracao_curso(String duracao_curso) {
        this.duracao_curso = duracao_curso;
    }
}
