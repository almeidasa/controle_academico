package entities;

/**
 *
 * @author Alexandre Almeida
 */
public class Usuarios {

    private int id_user;
    private String login;
    private String senha;
    private String tipo;

    public Usuarios(String login, String senha, String tipo) {
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }

    public Usuarios(int id_user, String login, String senha, String tipo) {
        this.id_user = id_user;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }

    public Usuarios() {
    }
    
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}