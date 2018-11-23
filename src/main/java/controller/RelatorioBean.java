package controller;

import DAO.AlunoDAO;
import DAO.CursoDAO;
import entities.Aluno;
import entities.Curso;
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

    private String codCurso;
    private String alunoCpf;
    private ArrayList<Aluno> alunos;
    private ArrayList<Curso> curso;
    private Map<String, String> ItensBoxAlunos;
    private Map<Integer, String> ItensBoxCurso;

    public RelatorioBean() {
        this.alunos = new ArrayList<>();
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
        ItensBoxAlunos = new LinkedHashMap<>();
        AlunoDAO al = new AlunoDAO();
        alunos = al.obterAlunosCurso(codCurso);

        for (Aluno aluno : alunos) {
            ItensBoxAlunos.put("A", "Selecione um Aluno");
            ItensBoxAlunos.put(aluno.getCpf(), aluno.getNome());
        }
    }
    
    //Getters e Seters
    public String getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(String codCurso) {    
        this.codCurso = codCurso;
    }

    public String getAlunoCpf() {
        return alunoCpf;
    }

    public void setAlunoCpf(String alunoCpf) {
        this.alunoCpf = alunoCpf;
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
}
