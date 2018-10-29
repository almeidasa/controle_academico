package controller;

import DAO.UsuariosDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @Autor Winder Rezende
 * @Data 28/10/2018, 01:58:19
 */
@ManagedBean
@SessionScoped
public class LoginBean {

    private String usuario;
    private String senha;
    private String nomeUsr;
    private String novaSenha;
    private String novaSenhaConf;
    private boolean erroNovaSenha;
    private boolean erroLogin;
    private boolean sessao = false;

    public String efetuarLogin() {
        UsuariosDAO login = new UsuariosDAO();
        erroLogin = false;

        if (login.verificaUsuarioSenha(usuario, senha)) {
            sessao = true;
            nomeUsr = login.obterUsuario(usuario);
            senha = null;
            return "index";
        } else {
            erroLogin = true;
            senha = null;
            return "login";
        }
    }

    public String logOff() {
        sessao = false;
        senha = null;
        return "login";
    }

    public String paginaAlterar() {
        UsuariosDAO login = new UsuariosDAO();
        erroLogin = false;
        novaSenha = null;
        novaSenhaConf = null;
        if (login.verificaUsuarioSenha(usuario, senha)) {
            sessao = true;
            return "alterar";
        } else {
            sessao = false;
            erroLogin = true;
            return "login";
        }
    }

    public String alterarSenha() {
        UsuariosDAO login = new UsuariosDAO();
        erroLogin = false;
        if (login.verificaUsuarioSenha(usuario, senha)) {
            if (novaSenha.equals(novaSenhaConf) && !novaSenha.equals("")) {
                login.alterarSenha(usuario, novaSenha);
                erroNovaSenha = false;
                senha = null;
                sessao = false;
                return "login";
            } else {
                erroNovaSenha = true;
                return "alterar";
            }
        } else {
            erroLogin = true;
            return "alterar";
        }
    }

    //Getters e Seters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isErroLogin() {
        return erroLogin;
    }

    public void setErroLogin(boolean erroLogin) {
        this.erroLogin = erroLogin;
    }

    public boolean isSessao() {
        return sessao;
    }

    public void setSessao(boolean sessao) {
        this.sessao = sessao;
    }

    public String getNomeUsr() {
        return nomeUsr;
    }

    public void setNomeUsr(String nomeUsr) {
        this.nomeUsr = nomeUsr;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getNovaSenhaConf() {
        return novaSenhaConf;
    }

    public void setNovaSenhaConf(String novaSenhaConf) {
        this.novaSenhaConf = novaSenhaConf;
    }

    public boolean isErroNovaSenha() {
        return erroNovaSenha;
    }

    public void setErroNovaSenha(boolean erroNovaSenha) {
        this.erroNovaSenha = erroNovaSenha;
    }
}
