package controller;

import DAO.CursoDAO;
import DAO.FuncionarioDAO;
import entities.Curso;
import entities.Funcionario;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author l-s-t
 */
@ManagedBean
@ViewScoped
public class CursoBean {

    private boolean readonly = false;
    private int cod_antigo;
    private int cod;
    private String nome_curso;
    private int fk_Funcionario_id;
    private ArrayList<Curso> cursos = new ArrayList<>();
    private ArrayList<Funcionario> func = new ArrayList<>();
    private Map<Integer, String> ItensBoxFuncionarios;

    private String botao = "Incluir";
    private String icone = "plus-circle";

    public CursoBean() {
        obter();
        setBoxFuncionarios();
    }

    private void obter() {
        CursoDAO us = new CursoDAO();
        cursos = us.obterCursos();
    }

    public void add() {
        if (botao.equals("Incluir")) {
            CursoDAO novo = new CursoDAO();
            Curso cs = new Curso(cod, nome_curso, fk_Funcionario_id);
            novo.inserirCurso(cs);
            limpaTela();
            obter();
        } else {
            CursoDAO novo = new CursoDAO();
            Curso cs = new Curso(cod, nome_curso, fk_Funcionario_id, cod_antigo);
            novo.editarCurso(cs);
            limpaTela();
            obter();

            readonly = false;
            botao = "Incluir";
            icone = "plus-circle";
        }
    }

    public void limpaTela() {
        cod_antigo = 0;
        cod = 0;
        nome_curso = "";
        fk_Funcionario_id = 0;
        readonly = false;
    }

    public String cancelar() {
        limpaTela();
        return "cadastrarCurso";
    }

    public void editar(Curso c) {
        cod_antigo = c.getCod();
        cod = c.getCod();
        nome_curso = c.getNome_curso();
        fk_Funcionario_id = c.getFk_Funcionario_id();

        readonly = true;
        botao = "Alterar";
        icone = "fa-refresh";
    }

    public void remover(Curso c) {
        cursos.remove(c);
        CursoDAO novo = new CursoDAO();
        novo.removerCurso(c);
    }

    private void setBoxFuncionarios() {
        ItensBoxFuncionarios = new LinkedHashMap<>();
        FuncionarioDAO us = new FuncionarioDAO();
        func = us.obterFuncionarios();

        for (Funcionario funcionarios : func) {
            ItensBoxFuncionarios.put(0, "Selecione um Funcionário");
            ItensBoxFuncionarios.put(funcionarios.getId(), funcionarios.getNome());
            System.out.println(funcionarios.getId());
        }
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

    public int getCod_antigo() {
        return cod_antigo;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public void setCod_antigo(int cod_antigo) {
        this.cod_antigo = cod_antigo;
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

    public int getFk_Funcionario_id() {
        return fk_Funcionario_id;
    }

    public void setFk_Funcionario_id(int fk_Funcionario_id) {
        this.fk_Funcionario_id = fk_Funcionario_id;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

    public ArrayList<Funcionario> getFunc() {
        return func;
    }

    public void setFunc(ArrayList<Funcionario> func) {
        this.func = func;
    }

    public Map<Integer, String> getItensBoxFuncionarios() {
        return ItensBoxFuncionarios;
    }

    public void setItensBoxFuncionarios(Map<Integer, String> ItensBoxFuncionarios) {
        this.ItensBoxFuncionarios = ItensBoxFuncionarios;
    }

}
