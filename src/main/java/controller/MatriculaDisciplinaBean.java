package controller;

import DAO.AlunoDAO;
import DAO.CursoDAO;
import DAO.DisciplinaDAO;
import DAO.MatriculaCursoDAO;
import DAO.MatriculaDisciplinaDAO;
import Util.Formatar;
import entities.Aluno;
import entities.Disciplina;
import entities.MatriculaCurso;
import entities.MatriculaDisciplina;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @Autor Alexandre Almeida
 * @Data 13/11/2018
 */
@ManagedBean
@ViewScoped
public class MatriculaDisciplinaBean {

    private boolean readonly = false;
    private int id;
    private String conceito;
    private String semestre;
    private int ano;
    private String situacao;
    private String fk_Disciplina_codigo;
    private String fk_Aluno_cpf;
    private int fk_Curso_cod;
    private int minDate = Integer.parseInt(Formatar.data(new Date(), "yyyy")) - 1;

    private ArrayList<MatriculaDisciplina> matriculaDisciplina;
    private ArrayList<MatriculaCurso> matriculaCurso;

    private ArrayList<Disciplina> disciplina;
    private ArrayList<Aluno> alunos;

    private Map<String, String> ItensBoxAlunos;
    private Map<Integer, String> ItensBoxCurso;
    private Map<String, String> ItensBoxDisciplina;

    private String botao = "Incluir";
    private String icone = "plus-circle";

    public MatriculaDisciplinaBean() {
        this.matriculaCurso = new ArrayList<>();
        this.matriculaDisciplina = new ArrayList<>();
        this.disciplina = new ArrayList<>();
        this.alunos = new ArrayList<>();
        ano = minDate;
        obter();
        setBoxAlunos();
    }

    private void obter() {
        matriculaDisciplina.clear();
        MatriculaDisciplinaDAO matDisciplinaDAO = new MatriculaDisciplinaDAO();
        matriculaDisciplina = matDisciplinaDAO.obterMatriculaDisciplina();
    }

    public void limpaTela() {
        id = 0;
        conceito = "";
        semestre = "";
        ano = minDate;
        situacao = "";
        fk_Disciplina_codigo = "";
        fk_Aluno_cpf = "";
        fk_Curso_cod = 0;
        readonly = false;
    }

    public void add() {
        if (botao.equals("Incluir")) {
            MatriculaDisciplinaDAO matriculaDisciplinaDAO = new MatriculaDisciplinaDAO();
            MatriculaDisciplina matDisciplina = new MatriculaDisciplina(conceito, semestre, ano, situacao, fk_Disciplina_codigo, fk_Aluno_cpf);
            matriculaDisciplinaDAO.inserirMatriculaDisciplina(matDisciplina);
            limpaTela();
            obter();
        } else {
            MatriculaDisciplinaDAO matriculaDisciplinaDAO = new MatriculaDisciplinaDAO();
            MatriculaDisciplina matDisciplina = new MatriculaDisciplina(id, conceito, semestre, ano, situacao, fk_Disciplina_codigo, fk_Aluno_cpf);
            matriculaDisciplinaDAO.editarMatriculaDisciplina(matDisciplina);
            limpaTela();
            obter();
            botao = "Incluir";
            icone = "plus-circle";
        }
    }

    public String cancelar() {
        limpaTela();
        return "matriculaDisciplina.xhtml";
    }

    public void editar(MatriculaDisciplina matDisciplina) {
        id = matDisciplina.getId();
        conceito = matDisciplina.getConceito();
        semestre = matDisciplina.getSemestre();
        ano = matDisciplina.getAno();
        situacao = matDisciplina.getSituacao();
        fk_Aluno_cpf = matDisciplina.getFk_Aluno_cpf();

        readonly = true;
        botao = "Alterar";
        icone = "fa-refresh";
    }

    public void remover(MatriculaDisciplina matDisciplina) {
        new MatriculaDisciplinaDAO().removerMatriculaDisciplina(matDisciplina);
        obter();
    }

    private void setBoxAlunos() {
        ItensBoxAlunos = new LinkedHashMap<>();
        AlunoDAO al = new AlunoDAO();
        alunos = al.obterAlunos();

        for (Aluno aluno : alunos) {
            ItensBoxAlunos.put("", "Selecione o Aluno");
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
            matriculaCurso = new MatriculaCursoDAO().obterMatriculaCurso();
            ItensBoxCurso.clear();
            for (MatriculaCurso matCurso : matriculaCurso) {
                ItensBoxCurso.put(0, "Selecione um Curso");
                if (matCurso.getFk_Aluno().equals(fk_Aluno_cpf)) {
                    ItensBoxCurso.put(matCurso.getFk_Curso_cod(), new CursoDAO().obterNomeCurso(matCurso.getFk_Curso_cod()));
                }
            }
        } catch (Exception ex) {
            System.out.println("Erro setBoxCurso: " + ex);
        }
    }

    private void setBoxDisciplina() {
        ItensBoxDisciplina = new LinkedHashMap<>();
        disciplina = new DisciplinaDAO().obterDisciplinaDisponivel(fk_Aluno_cpf, fk_Curso_cod);

        for (Disciplina disc : disciplina) {
            ItensBoxDisciplina.put("D", "Selecione uma Disciplina");
            ItensBoxDisciplina.put(disc.getCodigo(), disc.getNome());
        }
    }

    //Getters e Seters
    public int getFk_Curso_cod() {
        return fk_Curso_cod;
    }

    public void setFk_Curso_cod(int fk_Curso_cod) {
        this.fk_Curso_cod = fk_Curso_cod;
    }

    public String getConceito() {
        return conceito;
    }

    public void setConceito(String conceito) {
        this.conceito = conceito;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getFk_Disciplina_codigo() {
        return fk_Disciplina_codigo;
    }

    public void setFk_Disciplina_codigo(String fk_Disciplina_codigo) {
        this.fk_Disciplina_codigo = fk_Disciplina_codigo;
    }

    public String getFk_Aluno_cpf() {
        return fk_Aluno_cpf;
    }

    public void setFk_Aluno_cpf(String fk_Aluno_cpf) {
        this.fk_Aluno_cpf = fk_Aluno_cpf;
    }

    public ArrayList<MatriculaDisciplina> getMatriculaDisciplina() {
        return matriculaDisciplina;
    }

    public void setMatriculaDisciplina(ArrayList<MatriculaDisciplina> matriculaDisciplina) {
        this.matriculaDisciplina = matriculaDisciplina;
    }

    public ArrayList<MatriculaCurso> getMatriculaCurso() {
        return matriculaCurso;
    }

    public void setMatriculaCurso(ArrayList<MatriculaCurso> matriculaCurso) {
        this.matriculaCurso = matriculaCurso;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public int getMinDate() {
        return minDate;
    }

    public void setMinDate(int minDate) {
        this.minDate = minDate;
    }
}
