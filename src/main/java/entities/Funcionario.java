package entities;

/**
 *
 * @author Alexandre Almeida
 */
public class Funcionario {

    private int id;
    private String nome;
    private String telefone;
    private int fk_Usuarios_id_user;

    public Funcionario(String nome, String telefone, int fk_Usuarios_id_user) {
        this.nome = nome;
        this.telefone = telefone;
        this.fk_Usuarios_id_user = fk_Usuarios_id_user;
    }

    public Funcionario(int id, String nome, String telefone, int fk_Usuarios_id_user) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.fk_Usuarios_id_user = fk_Usuarios_id_user;
    }

    public Funcionario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getFk_Usuarios_id_user() {
        return fk_Usuarios_id_user;
    }

    public void setFk_Usuarios_id_user(int fk_Usuarios_id_user) {
        this.fk_Usuarios_id_user = fk_Usuarios_id_user;
    }
}
