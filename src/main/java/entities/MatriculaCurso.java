package entities;

/**
 *
 * @author Alexandre Almeida
 */
public class MatriculaCurso {

    private int matricula;
    private String situacao;
    private String data_inicio;
    private String duracao_curso;
    private String fk_Aluno;
    private int fk_Curso_cod;

    public MatriculaCurso(int matricula, String situacao, String data_inicio, String duracao_curso, String fk_Aluno, int fk_Curso_cod) {
        this.matricula = matricula;
        this.situacao = situacao;
        this.data_inicio = data_inicio;
        this.duracao_curso = duracao_curso;
        this.fk_Aluno = fk_Aluno;
        this.fk_Curso_cod = fk_Curso_cod;
    }

    public MatriculaCurso() {
    }

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

    public String getFk_Aluno() {
        return fk_Aluno;
    }

    public void setFk_Aluno(String fk_Aluno) {
        this.fk_Aluno = fk_Aluno;
    }

    public int getFk_Curso_cod() {
        return fk_Curso_cod;
    }

    public void setFk_Curso_cod(int fk_Curso_cod) {
        this.fk_Curso_cod = fk_Curso_cod;
    }
}
