package entities;

/**
 *
 * @author Alexandre Almeida
 */
public class Funcionario {

    private int id;
    private String nome;
    private String email;
    private String telefone;
    private int fk_Usuarios_id_user;

    public Funcionario() {
    }
    
    public Funcionario(String nome, String email, String telefone, int fk_Usuarios_id_user) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.fk_Usuarios_id_user = fk_Usuarios_id_user;
    }

    public Funcionario(int id, String nome, String email, String telefone, int fk_Usuarios_id_user) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.fk_Usuarios_id_user = fk_Usuarios_id_user;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
