package controller;

import DAO.AlunoDAO;
import DAO.CursoDAO;
import DAO.DisciplinaDAO;
import DAO.FuncionarioDAO;
import DAO.MatriculaCursoDAO;
import DAO.MatriculaDisciplinaDAO;
import entities.Aluno;
import entities.Curso;
import entities.Disciplina;
import entities.MatriculaCurso;
import entities.MatriculaDisciplina;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @Autor Alexandre Almeida
 * @Data 15/11/2018
 */
@ManagedBean
@ViewScoped
public class AprovacaoBean {

    private boolean conceito_readonly;
    private String situacao;
    private String conceito;
    private int fk_Curso_cod;
    private String fk_Disciplina_codigo;

    private Map<String, String> ItensBoxAlunos;
    private Map<Integer, String> ItensBoxCurso;
    private Map<String, String> ItensBoxDisciplina;

    private ArrayList<MatriculaDisciplina> matriculaDisciplina;
    private ArrayList<Disciplina> disciplina;
    private ArrayList<Aluno> alunos;
    private ArrayList<Curso> curso;

    //construtores e métodos
    public AprovacaoBean() {
        this.matriculaDisciplina = new ArrayList<>();

        this.disciplina = new ArrayList<>();
        this.alunos = new ArrayList<>();
        this.curso = new ArrayList<>();

        setBoxCurso();
        setBoxDisciplina();
        setBoxAlunos();
    }

    public AprovacaoBean(boolean conceito_readonly, String situacao, String conceito, int fk_Curso_cod, String fk_Disciplina_codigo) {
        this.conceito_readonly = conceito_readonly;
        this.situacao = situacao;
        this.conceito = conceito;
        this.fk_Curso_cod = fk_Curso_cod;
        this.fk_Disciplina_codigo = fk_Disciplina_codigo;
    }

    public String limpar() {
        return "aprovacao.xhtml";
    }

    public void buscar() {
        obter();
    }

    private void obter() {
        matriculaDisciplina.clear();
        MatriculaDisciplinaDAO matDisciplinaDAO = new MatriculaDisciplinaDAO();
        matriculaDisciplina = matDisciplinaDAO.obterMatriculaPorDisciplina(fk_Disciplina_codigo);
    }

    private void setBoxAlunos() {
        ItensBoxAlunos = new LinkedHashMap<>();
        AlunoDAO al = new AlunoDAO();
        alunos = al.obterAlunos();

        for (Aluno aluno : alunos) {
            ItensBoxAlunos.put(aluno.getCpf(), aluno.getNome());
        }
    }

    public void obterBoxCurso() {
        setBoxCurso();
    }

    public void obterBoxDisciplina() {
        setBoxDisciplina();
    }

    private void setBoxCurso() {
        try {
            ItensBoxCurso = new LinkedHashMap<>();
            if (LoginBean.tipo.equals("Administrador")) {
                curso = new CursoDAO().obterCursos();
            } else {
                curso = new CursoDAO().obterCursosPorCoordenador(new FuncionarioDAO().obterFuncionarioPorUsuario(LoginBean.id_logado));
            }
            for (Curso c : curso) {
                ItensBoxCurso.put(0, "Selecione um Curso");
                ItensBoxCurso.put(c.getCod(), c.getNome_curso());
            }
        } catch (Exception ex) {
            System.out.println("Erro setBoxCurso: " + ex);
        }
    }

    private void setBoxDisciplina() {
        ItensBoxDisciplina = new LinkedHashMap<>();
        disciplina = new DisciplinaDAO().obterDisciplina();
        for (Disciplina disc : disciplina) {
            ItensBoxDisciplina.put("", "Selecione uma Disciplina");
            if (disc.getFk_Curso_cod() == fk_Curso_cod) {
                ItensBoxDisciplina.put(disc.getCodigo(), disc.getNome());
            }
        }
    }

    public void alteraSituacao(MatriculaDisciplina m) {
        conceito_readonly = !m.getSituacao().equals("Concluida");

    }

    public String verificaConceito(MatriculaDisciplina m) {
        return m.getConceito();
    }

    public boolean verificaSituacao(MatriculaDisciplina m) {
        conceito_readonly = !m.getSituacao().equals("Concluida");
        return conceito_readonly;
    }

    //getSeters
    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public boolean isConceito_readonly() {
        return conceito_readonly;
    }

    public void setConceito_readonly(boolean conceito_readonly) {
        this.conceito_readonly = conceito_readonly;
    }

    public String getConceito() {
        return conceito;
    }

    public void setConceito(String conceito) {
        this.conceito = conceito;
    }

    public Map<String, String> getItensBoxAlunos() {
        return ItensBoxAlunos;
    }

    public void setItensBoxAlunos(Map<String, String> ItensBoxAlunos) {
        this.ItensBoxAlunos = ItensBoxAlunos;
    }

    public Map<Integer, String> getItensBoxCurso() {
        return ItensBoxCurso;
    }

    public void setItensBoxCurso(Map<Integer, String> ItensBoxCurso) {
        this.ItensBoxCurso = ItensBoxCurso;
    }

    public Map<String, String> getItensBoxDisciplina() {
        return ItensBoxDisciplina;
    }

    public void setItensBoxDisciplina(Map<String, String> ItensBoxDisciplina) {
        this.ItensBoxDisciplina = ItensBoxDisciplina;
    }

    public ArrayList<MatriculaDisciplina> getMatriculaDisciplina() {
        return matriculaDisciplina;
    }

    public void setMatriculaDisciplina(ArrayList<MatriculaDisciplina> matriculaDisciplina) {
        this.matriculaDisciplina = matriculaDisciplina;
    }

    public ArrayList<Disciplina> getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(ArrayList<Disciplina> disciplina) {
        this.disciplina = disciplina;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public ArrayList<Curso> getCurso() {
        return curso;
    }

    public void setCurso(ArrayList<Curso> curso) {
        this.curso = curso;
    }

    public int getFk_Curso_cod() {
        return fk_Curso_cod;
    }

    public void setFk_Curso_cod(int fk_Curso_cod) {
        this.fk_Curso_cod = fk_Curso_cod;
    }

    public String getFk_Disciplina_codigo() {
        return fk_Disciplina_codigo;
    }

    public void setFk_Disciplina_codigo(String fk_Disciplina_codigo) {
        this.fk_Disciplina_codigo = fk_Disciplina_codigo;
    }

}
