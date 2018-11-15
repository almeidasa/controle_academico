package controller;

import DAO.AlunoDAO;
import DAO.MatriculaCursoDAO;
import DAO.MatriculaDisciplinaDAO;
import Util.Formatar;
import entities.Aluno;
import entities.Curso;
import entities.MatriculaCurso;
import entities.MatriculaDisciplina;
import java.util.ArrayList;
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

    private int matricula;
    private String situacao;
    private String data_inicio;
    private String duracao_curso;
    private String fk_Aluno;
    private int fk_Curso_cod;

    private ArrayList<MatriculaDisciplina> matriculaDisciplina;
    private Map<String, String> ItensBoxAlunos;
    private Map<Integer, String> ItensBoxDisciplina;    
    private ArrayList<Aluno> alunos;
    private ArrayList<MatriculaCurso> matCurso;


    private String botao = "Incluir";
    private String icone = "plus-circle";

    public MatriculaDisciplinaBean() {
        this.alunos = new ArrayList<>();
        this.matCurso = new ArrayList<>();
        this.matriculaDisciplina = new ArrayList<>();
        obter();
        setBoxAlunos();
        setBoxDisciplina();
    }

    public MatriculaDisciplinaBean(int matricula, String situacao, String data_inicio, String duracao_curso, String fk_Aluno, int fk_Curso_cod) {
        this.matricula = matricula;
        this.situacao = situacao;
        this.data_inicio = data_inicio;
        this.duracao_curso = duracao_curso;
        this.fk_Aluno = fk_Aluno;
        this.fk_Curso_cod = fk_Curso_cod;
    }

    private void obter() {
        matriculaDisciplina.clear();
        MatriculaDisciplinaDAO matDisciplinaDAO = new MatriculaDisciplinaDAO();
        matriculaDisciplina = matDisciplinaDAO.obterMatriculaDisciplina();
    }

    public void limpaTela() {
        matricula = 0;
        situacao = "";
        data_inicio = "";
        duracao_curso = "";
        fk_Curso_cod = 0;
        fk_Aluno = "A";
        System.out.println("Teste");
    }

    public void teste(){
        System.out.println(       );
    }
    
    public void add() {
        if (botao.equals("Incluir")) {
            MatriculaCursoDAO matCursoDAO = new MatriculaCursoDAO();
            MatriculaCurso matCurso = new MatriculaCurso(Integer.parseInt(fk_Curso_cod + fk_Aluno.replace(".", "").replace("-", "").substring(0, 5)),
                    situacao, data_inicio, duracao_curso, fk_Aluno, fk_Curso_cod);
            matCursoDAO.inserirMatriculaCurso(matCurso);
            limpaTela();
            obter();
        } else {
            MatriculaCursoDAO matCursoDAO = new MatriculaCursoDAO();
            MatriculaCurso matCurso = new MatriculaCurso(matricula, situacao, data_inicio, duracao_curso, fk_Aluno, fk_Curso_cod);
            matCursoDAO.editarMatriculaCurso(matCurso);
            limpaTela();
            obter();
            botao = "Incluir";
            icone = "plus-circle";
        }
    }

    public String cancelar() {
        limpaTela();
        return "matriculaCurso.xhtml";
    }

    public void editar(MatriculaCurso mc) {
        matricula = mc.getMatricula();
        situacao = mc.getSituacao();
        data_inicio = Formatar.Data(mc.getData_inicio(), "dd/MM/yyyy", "yyyy-MM-dd");
        duracao_curso = mc.getDuracao_curso();
        fk_Aluno = mc.getFk_Aluno();
        fk_Curso_cod = mc.getFk_Curso_cod();

        botao = "Alterar";
        icone = "fa-refresh";
    }

    public void remover(MatriculaCurso matcurso) {
        MatriculaCursoDAO matCursoDAO = new MatriculaCursoDAO();
        matCursoDAO.removerMatriculaCurso(matcurso);
        obter();
    }

    private void setBoxAlunos() {
        ItensBoxAlunos = new LinkedHashMap<>();
        AlunoDAO al = new AlunoDAO();
        alunos = al.obterAlunos();

        for (Aluno aluno : alunos) {
            ItensBoxAlunos.put("A", "Selecione um Aluno");
            ItensBoxAlunos.put(aluno.getCpf(), aluno.getNome());
        }
    }

    private void setBoxDisciplina() {
        ItensBoxDisciplina = new LinkedHashMap<>();
        
        if(fk_Aluno.equals("A")){
            ItensBoxDisciplina.put(0, "Selecione um Aluno");
        } else {
            matCurso = new MatriculaCursoDAO().obterCursoDoAluno(fk_Aluno);
            
            
        }

//        for (Curso cursos : curso) {
//            ItensBoxDisciplina.put(0, "Selecione um Curso");
//            ItensBoxDisciplina.put(cursos.getCod(), cursos.getNome_curso());
//        }
    }

    //Getters e Seters
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getDuracao_curso() {
        return duracao_curso;
    }

    public void setDuracao_curso(String duracao_curso) {
        this.duracao_curso = duracao_curso;
    }

    public String getFk_Aluno() {
        return fk_Aluno;
    }

    public void setFk_Aluno(String fk_Aluno) {
        this.fk_Aluno = fk_Aluno;
    }

    public int getFk_Curso_cod() {
        return fk_Curso_cod;
    }

    public void setFk_Curso_cod(int fk_Curso_cod) {
        this.fk_Curso_cod = fk_Curso_cod;
    }

    public ArrayList<MatriculaDisciplina> getMatriculaDisciplina() {
        return matriculaDisciplina;
    }

    public void setMatriculaDisciplina(ArrayList<MatriculaDisciplina> matriculaDisciplina) {
        this.matriculaDisciplina = matriculaDisciplina;
    }



    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public ArrayList<MatriculaCurso> getMatCurso() {
        return matCurso;
    }

    public void setMatCurso(ArrayList<MatriculaCurso> matCurso) {
        this.matCurso = matCurso;
    }



    public Map<String, String> getItensBoxAlunos() {
        return ItensBoxAlunos;
    }

    public void setItensBoxAlunos(Map<String, String> ItensBoxAlunos) {
        this.ItensBoxAlunos = ItensBoxAlunos;
    }

    public Map<Integer, String> getItensBoxDisciplina() {
        return ItensBoxDisciplina;
    }

    public void setItensBoxDisciplina(Map<Integer, String> ItensBoxDisciplina) {
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

}
