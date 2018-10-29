package controller;

import DAO.UsuariosDAO;
import Util.Exibir;
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
    private boolean sessao = false;

    public String efetuarLogin() {
        UsuariosDAO login = new UsuariosDAO();

        if (login.verificaUsuarioSenha(usuario, senha)) {
            sessao = true;
            nomeUsr = login.obterUsuario(usuario);
            senha = null;
            return "index";
        } else {
            Exibir.menssagem("Usuário ou senha inválidos!");
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
        novaSenha = null;
        novaSenhaConf = null;
        if (login.verificaUsuarioSenha(usuario, senha)) {
            sessao = true;
            return "alterar";
        } else {
            sessao = false;
            Exibir.menssagem("Usuário ou senha inválidos!");
            return "login";
        }
    }

    public String alterarSenha() {
        UsuariosDAO login = new UsuariosDAO();
        if (login.verificaUsuarioSenha(usuario, senha)) {
            if (novaSenha.equals(novaSenhaConf) && !novaSenha.equals("")) {
                login.alterarSenha(usuario, novaSenha);
                senha = null;
                sessao = false;
                Exibir.menssagem("A senha foi alterada!");
                return "login";
            } else {
                Exibir.menssagem("A nova senha e a confirmação devem ser iguais!");
                return "alterar";
            }
        } else {
            Exibir.menssagem("Usuário ou senha inválidos!");
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

    public boolean isSessao() {
        return sessao;
    }

    public void setSessao(boolean sessao) {
        this.sessao = sessao;
    }
}
