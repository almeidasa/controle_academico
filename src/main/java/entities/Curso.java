package entities;

/**
 *
 * @author Alexandre Almeida
 */
public class Curso {

    private int cod_antigo;
    private int cod;
    private String nome_curso;
    private int fk_Funcionario_id;

    public Curso(int cod, String nome_curso, int fk_Funcionario_id) {
        this.cod = cod;
        this.nome_curso = nome_curso;
        this.fk_Funcionario_id = fk_Funcionario_id;
    }

    public Curso(int cod, String nome_curso, int fk_Funcionario_id, int cod_antigo) {
        this.cod_antigo = cod_antigo;
        this.cod = cod;
        this.nome_curso = nome_curso;
        this.fk_Funcionario_id = fk_Funcionario_id;
    }

    public int getCod_antigo() {
        return cod_antigo;
    }

    public void setCod_antigo(int cod_antigo) {
        this.cod_antigo = cod_antigo;
    }

    public Curso() {
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome_curso() {
        return nome_curso;
    }

    public void setNome_curso(String nome_curso) {
        this.nome_curso = nome_curso;
    }

    public int getFk_Funcionario_id() {
        return fk_Funcionario_id;
    }

    public void setFk_Funcionario_id(int fk_Funcionario_id) {
        this.fk_Funcionario_id = fk_Funcionario_id;
    }
}
