package controller;

import DAO.CursoDAO;
import DAO.DisciplinaDAO;
import DAO.RelatorioDAO;
import entities.Curso;
import entities.Disciplina;
import entities.HistoricoAluno;
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
public class MatriculadosBean {

    private int codCurso;
    private String codDicplina = "D";
    private ArrayList<Curso> curso;
    private ArrayList<HistoricoAluno> historico;
    private ArrayList<Disciplina> diciplina;
    private Map<Integer, String> ItensBoxCurso;
    private Map<String, String> ItensBoxDisciplina;

    public MatriculadosBean() {
        this.curso = new ArrayList<>();
        setBoxCurso();
        setBoxDisciplina();
        obterAlunosMatriculados();
    }
    
    private void setBoxCurso() {
        ItensBoxCurso = new LinkedHashMap<>();
        CursoDAO c = new CursoDAO();
        curso = c.obterCursos();

        for (Curso cursos : curso) {
            ItensBoxCurso.put(0, "Selecione um Curso");
            ItensBoxCurso.put(cursos.getCod(), cursos.getNome_curso() + " (" + cursos.getCod() + ")");
        }
    }
    
    private void setBoxDisciplina() {
        ItensBoxDisciplina = new LinkedHashMap<>();
        DisciplinaDAO disc = new DisciplinaDAO();
        diciplina = disc.obterDisciplina();

        for (Disciplina diciplinas : diciplina) {
            ItensBoxDisciplina.put("D", "Selecione uma Disciplina");
            ItensBoxDisciplina.put(diciplinas.getCodigo(), diciplinas.getNome() + " (" + diciplinas.getFk_Curso_cod() + ")");
        }
    }
    
    public void relAlunosMatriculados(){
        obterAlunosMatriculados();
    }
    
    private void obterAlunosMatriculados(){
        this.historico = new ArrayList<>();
        RelatorioDAO rel = new RelatorioDAO();
        historico = rel.obterAlunosMatriculados(codCurso, codDicplina);
    }
    
    //Getters e Seters
    public int getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
    }

    public String getCodDicplina() {
        return codDicplina;
    }

    public void setCodDicplina(String codDicplina) {
        this.codDicplina = codDicplina;
    }

    public ArrayList<Curso> getCurso() {
        return curso;
    }

    public void setCurso(ArrayList<Curso> curso) {
        this.curso = curso;
    }

    public ArrayList<HistoricoAluno> getHistorico() {
        return historico;
    }

    public void setHistorico(ArrayList<HistoricoAluno> historico) {
        this.historico = historico;
    }

    public ArrayList<Disciplina> getDiciplina() {
        return diciplina;
    }

    public void setDiciplina(ArrayList<Disciplina> diciplina) {
        this.diciplina = diciplina;
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
}
