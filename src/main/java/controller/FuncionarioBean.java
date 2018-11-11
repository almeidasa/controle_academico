package controller;

import DAO.FuncionarioDAO;
import DAO.UsuariosDAO;
import entities.Funcionario;
import entities.Usuarios;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @Autor Alexandre Almeida
 * @Data 07/11/2018
 */
@ManagedBean
@ViewScoped
public class FuncionarioBean {

    private int id;
    private String nome;
    private String cargo;
    private String email;
    private String telefone;
    private int fk_Usuarios_id_user;
    private ArrayList<Funcionario> funcionario;
    private ArrayList<Usuarios> user;
    private Map<Integer, String> ItensBoxUsuarios;

    private String botao = "Incluir";
    private String icone = "plus-circle";

    public FuncionarioBean() {
        this.user = new ArrayList<>();
        this.funcionario = new ArrayList<>();
        obter();
        setBoxUsuarios();
    }

    private void obter() {
        funcionario.clear();
        FuncionarioDAO func = new FuncionarioDAO();
        funcionario = func.obterFuncionarios();
    }

    public void limpaTela() {
        id = 0;
        nome = "";
        cargo = "";
        email = "";
        telefone = "";
        fk_Usuarios_id_user = 0;
        botao = "Incluir";
        icone = "plus-circle";
    }

    public void add() {
        if (botao.equals("Incluir")) {
            FuncionarioDAO func = new FuncionarioDAO();
            Funcionario fc = new Funcionario(nome, cargo, email, telefone, fk_Usuarios_id_user);
            func.inserirFuncionario(fc);
            limpaTela();
            obter();
        } else {
            FuncionarioDAO func = new FuncionarioDAO();
            Funcionario fc = new Funcionario(id, nome, cargo, email, telefone, fk_Usuarios_id_user);
            func.editarFuncionario(fc);
            limpaTela();
            obter();
            botao = "Incluir";
            icone = "plus-circle";
        }
    }

    public String cancelar() {
        limpaTela();
        return "cadastrarFuncionario";
    }

    public void editar(Funcionario f) {
        id = f.getId();
        nome = f.getNome();
        cargo = f.getCargo();
        email = f.getEmail();
        telefone = f.getTelefone();
        fk_Usuarios_id_user = f.getFk_Usuarios_id_user();
        botao = "Alterar";
        icone = "fa-refresh";
    }

    public void remover(Funcionario f) {
        funcionario.remove(f);
        FuncionarioDAO func = new FuncionarioDAO();
        func.removerFuncionario(f);
    }

    private void setBoxUsuarios() {
        ItensBoxUsuarios = new LinkedHashMap<>();
        UsuariosDAO us = new UsuariosDAO();
        user = us.obterUsuarios();

        for (Usuarios usuario : user) {
            if (usuario.getSituacao().equals("Ativo")) {
                ItensBoxUsuarios.put(0, "Selecione um Usuário");
                ItensBoxUsuarios.put(usuario.getId_user(), usuario.getLogin());
            }
        }
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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

    public ArrayList<Funcionario> getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(ArrayList<Funcionario> funcionario) {
        this.funcionario = funcionario;
    }

    public ArrayList<Usuarios> getUser() {
        return user;
    }

    public void setUser(ArrayList<Usuarios> user) {
        this.user = user;
    }

    public Map<Integer, String> getItensBoxUsuarios() {
        return ItensBoxUsuarios;
    }

    public void setItensBoxUsuarios(Map<Integer, String> ItensBoxUsuarios) {
        this.ItensBoxUsuarios = ItensBoxUsuarios;
    }

    public String getBotao() {
        return botao;
    }

    public void setBotao(String botao) {
        this.botao = botao;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }
}
