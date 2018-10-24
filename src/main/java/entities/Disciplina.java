package entities;

/**
 *
 * @author Alexandre Almeida
 */
public class Disciplina {

    private String codigo;
    private String nome;
    private String situacao;
    private int fk_Curso_cod;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public int getFk_Curso_cod() {
        return fk_Curso_cod;
    }

    public void setFk_Curso_cod(int fk_Curso_cod) {
        this.fk_Curso_cod = fk_Curso_cod;
    }

}
