package controller;

import DAO.AlunoDAO;
import DAO.CursoDAO;
import DAO.MatriculaCursoDAO;
import DAO.RelatorioDAO;
import Util.Relatorio;
import entities.Aluno;
import entities.Curso;
import entities.HistoricoAluno;
import entities.MatriculaCurso;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @Autor Winder Rezende
 * @Data 20/11/2018
 */
@ManagedBean
@ViewScoped
public class RelatorioBean {

    private int codCurso;
    private String alunoCpf;
    private String situacao;
    private ArrayList<Aluno> alunos;
    private ArrayList<Curso> curso;
    private ArrayList<MatriculaCurso> matriculaCurso; 
    private ArrayList<HistoricoAluno> historico;
    private Map<String, String> ItensBoxAlunos;
    private Map<Integer, String> ItensBoxCurso;
    private Map<String, Integer> matriculaAluno;

    public RelatorioBean() {
        this.curso = new ArrayList<>();
        setBoxCurso();
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
    
    public void setBoxAlunos() {
        this.alunos = new ArrayList<>();
        AlunoDAO al = new AlunoDAO();
        alunos = al.obterAlunosCurso(codCurso);

        this.ItensBoxAlunos = new LinkedHashMap<>();
        for (Aluno aluno : alunos) {
            ItensBoxAlunos.put("A", "Selecione um Aluno");
            ItensBoxAlunos.put(aluno.getCpf(), aluno.getNome());
        }
    }
    
    public void obterHistoricoAluno(){
        this.historico = new ArrayList<>();
        RelatorioDAO rel = new RelatorioDAO();
        historico = rel.obterHistorico(alunoCpf, codCurso);
        rel.obterFoto(alunoCpf);
    }
    
    public void gerarRelatorio(){
        Relatorio gerar = new Relatorio();
        gerar.getHistoricoAluno(historico);
    }
    
    public void relAlunosPodemColarGrau(){
        this.alunos = new ArrayList<>();
        RelatorioDAO rel = new RelatorioDAO();
        alunos = rel.obterPodemColarGrau(codCurso);
        
        MatriculaCursoDAO mat = new MatriculaCursoDAO();
        matriculaCurso = mat.obterMatriculaCurso();
        
        this.matriculaAluno = new LinkedHashMap<>();
        for (MatriculaCurso mtc : matriculaCurso) {
            if (mtc.getFk_Curso_cod() == codCurso) {
                matriculaAluno.put(mtc.getFk_Aluno(), mtc.getMatricula());
            }
        }
    }
    
    public void relAlunosMatriculados(){
        this.historico = new ArrayList<>();
        RelatorioDAO rel = new RelatorioDAO();
        historico = rel.obterAlunosMatriculados(codCurso, situacao);
    }
    
    //Getters e Seters
    public int getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
    }

    public String getAlunoCpf() {
        return alunoCpf;
    }

    public void setAlunoCpf(String alunoCpf) {
        this.alunoCpf = alunoCpf;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
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

    public ArrayList<MatriculaCurso> getMatriculaCurso() {
        return matriculaCurso;
    }

    public void setMatriculaCurso(ArrayList<MatriculaCurso> matriculaCurso) {
        this.matriculaCurso = matriculaCurso;
    }

    public ArrayList<HistoricoAluno> getHistorico() {
        return historico;
    }

    public void setHistorico(ArrayList<HistoricoAluno> historico) {
        this.historico = historico;
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

    public Map<String, Integer> getMatriculaAluno() {
        return matriculaAluno;
    }

    public void setMatriculaAluno(Map<String, Integer> matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
    }
}
