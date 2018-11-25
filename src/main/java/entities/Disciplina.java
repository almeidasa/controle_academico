package entities;

/**
 *
 * @author Alexandre Almeida
 */
public class Disciplina {

    private String cod_antigo;
    private String codigo;
    private String nome;
    private String situacao;
    private int fk_Curso_cod;
    
    public Disciplina(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Disciplina(String codigo, String nome, String situacao, int fk_Curso_cod) {
        this.codigo = codigo;
        this.nome = nome;
        this.situacao = situacao;
        this.fk_Curso_cod = fk_Curso_cod;
    }

    public Disciplina(String codigo, String nome, String situacao, int fk_Curso_cod, String cod_antigo) {
        this.cod_antigo = cod_antigo;
        this.codigo = codigo;
        this.nome = nome;
        this.situacao = situacao;
        this.fk_Curso_cod = fk_Curso_cod;
    }

    public String getCod_antigo() {
        return cod_antigo;
    }

    public void setCod_antigo(String cod_antigo) {
        this.cod_antigo = cod_antigo;
    }

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
