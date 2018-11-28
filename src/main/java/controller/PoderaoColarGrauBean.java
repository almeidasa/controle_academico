package controller;

import DAO.CursoDAO;
import DAO.MatriculaCursoDAO;
import DAO.RelatorioDAO;
import entities.Aluno;
import entities.Curso;
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
public class PoderaoColarGrauBean {

    private int codCurso;
    private ArrayList<Aluno> alunos;
    private ArrayList<Curso> curso;
    private ArrayList<MatriculaCurso> matriculaCurso;
    private Map<Integer, String> ItensBoxCurso;
    private Map<String, Integer> matriculaAluno;

    public PoderaoColarGrauBean() {
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
    
    public void relAlunosPodemColarGrau(){
        this.alunos = new ArrayList<>();
        RelatorioDAO rel = new RelatorioDAO();
        alunos = rel.obterPoderaoColarGrau(codCurso);
        
        MatriculaCursoDAO mat = new MatriculaCursoDAO();
        matriculaCurso = mat.obterMatriculaCurso();
        
        this.matriculaAluno = new LinkedHashMap<>();
        for (MatriculaCurso mtc : matriculaCurso) {
            if (mtc.getFk_Curso_cod() == codCurso) {
                matriculaAluno.put(mtc.getFk_Aluno(), mtc.getMatricula());
            }
        }
    }
    
    //Getters e Seters
    public int getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
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
