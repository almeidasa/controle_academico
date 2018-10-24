package entities;

import java.sql.Date;

/**
 *
 * @author Alexandre Almeida
 */
public class MatriculaCurso {
    
    private int matricula;
    private String situacao;
    private Date data_inicio;
    private String duracao_curso;
    private String fk_Aluno;
    private String fk_Curso_cod;

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getDuracao_curso() {
        return duracao_curso;
    }

    public void setDuracao_curso(String duracao_curso) {
        this.duracao_curso = duracao_curso;
    }

    public String getFk_Aluno() {
        return fk_Aluno;
    }

    public void setFk_Aluno(String fk_Aluno) {
        this.fk_Aluno = fk_Aluno;
    }

    public String getFk_Curso_cod() {
        return fk_Curso_cod;
    }

    public void setFk_Curso_cod(String fk_Curso_cod) {
        this.fk_Curso_cod = fk_Curso_cod;
    }
}
