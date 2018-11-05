package entities;

import java.sql.Date;

/**
 *
 * @author Alexandre Almeida
 */
public class Usuarios {

    private int id_user;
    private String login;
    private String senha;
    private String tipo;
    private String situacao;
    private String data_cad;

    public Usuarios() {
    }
    
    public Usuarios( String login, String senha, String tipo, String situacao) {
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
        this.situacao = situacao;
    }

    public Usuarios(int id_user, String login, String senha, String tipo, String situacao, String data_cad) {
        this.id_user = id_user;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
        this.situacao = situacao;
        this.data_cad = data_cad;
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getData_cad() {
        return data_cad;
    }

    public void setData_cad(String data_cad) {
        this.data_cad = data_cad;
    }
}
