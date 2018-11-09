package controller;

import DAO.CursoDAO;
import DAO.DisciplinaDAO;
import DAO.FuncionarioDAO;
import DAO.UsuariosDAO;
import entities.Curso;
import entities.Disciplina;
import entities.Funcionario;
import entities.Usuarios;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @Autor Alexandre Almeida
 * @Data 07/11/2018
 */
@ManagedBean
@SessionScoped
public class DisciplinaBean {
    
    private String codigo;
    private String nome;
    private String situacao;
    private int fk_curso_cod;
    private ArrayList<Disciplina> disciplina;
    private ArrayList<Curso> curso;
    private Map<Integer, String> ItensBoxCurso;
    
    private String botao = "Incluir";
    private String icone = "plus-circle";
    
    public DisciplinaBean() {
        this.curso = new ArrayList<>();
        this.disciplina = new ArrayList<>();
        obter();
        setBoxCurso();
    }
    
    private void obter() {
        disciplina.clear();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        disciplina = disciplinaDAO.obterDisciplina();
    }
    
    public void limpaTela() {
        codigo = "";
        nome = "";
        situacao = "";
        fk_curso_cod = 0;
    }
    
    public void add() {
        if (botao.equals("Incluir")) {
            DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
            Disciplina disc = new Disciplina(codigo, nome, situacao, fk_curso_cod);
            disciplinaDAO.inserirDisciplina(disc);
            limpaTela();
            obter();
        } else {
            DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
            Disciplina disc = new Disciplina(codigo, nome, situacao, fk_curso_cod);
            disciplinaDAO.editarDisciplina(disc);
            limpaTela();
            obter();
            botao = "Incluir";
            icone = "plus-circle";
        }
    }
    
    public String cancelar() {
        limpaTela();
        return "cadastrarDisciplina";
    }
    
    public void editar(Disciplina d) {
        codigo = d.getCodigo();
        nome = d.getNome();
        situacao = d.getSituacao();
        fk_curso_cod = d.getFk_Curso_cod();
        
        botao = "Alterar";
        icone = "fa-refresh";
    }
    
    public void remover(Disciplina d) {
        disciplina.remove(d);
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        disciplinaDAO.removerDisciplina(d);
    }
    
    private void setBoxCurso() {
        ItensBoxCurso = new LinkedHashMap<>();
        CursoDAO c = new CursoDAO();
        curso = c.obterCursos();
        
        for (Curso cursos : curso) {
            ItensBoxCurso.put(0, "Selecione um Curso");
            ItensBoxCurso.put(cursos.getCod(), cursos.getNome_curso());
        }
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
    
    public int getFk_curso_cod() {
        return fk_curso_cod;
    }
    
    public void setFk_curso_cod(int fk_curso_cod) {
        this.fk_curso_cod = fk_curso_cod;
    }
    
    public ArrayList<Disciplina> getDisciplina() {
        return disciplina;
    }
    
    public void setDisciplina(ArrayList<Disciplina> disciplina) {
        this.disciplina = disciplina;
    }
    
    public ArrayList<Curso> getCurso() {
        return curso;
    }
    
    public void setCurso(ArrayList<Curso> curso) {
        this.curso = curso;
    }
    
    public Map<Integer, String> getItensBoxCurso() {
        return ItensBoxCurso;
    }
    
    public void setItensBoxCurso(Map<Integer, String> ItensBoxCurso) {
        this.ItensBoxCurso = ItensBoxCurso;
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
