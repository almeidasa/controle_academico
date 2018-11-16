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

    private Map<String, String> ItensBoxAlunos;
    private Map<Integer, String> ItensBoxCurso;
    private Map<String, String> ItensBoxDisciplina;

    private ArrayList<MatriculaDisciplina> matriculaDisciplina;
    private ArrayList<Disciplina> disciplina;
    private ArrayList<Aluno> alunos;
    private ArrayList<Curso> curso;

    //construtores e métodos
    public AprovacaoBean() {
        
        setBoxCurso();
    }

    private void obter() {
        matriculaDisciplina.clear();
        MatriculaDisciplinaDAO matDisciplinaDAO = new MatriculaDisciplinaDAO();
        matriculaDisciplina = matDisciplinaDAO.obterMatriculaDisciplina();
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
            curso = new CursoDAO().obterCursosPorCoordenador(new FuncionarioDAO().obterFuncionarioPorUsuario(new LoginBean().getId_user()));
            ItensBoxCurso.clear();
            for (Curso c : curso) {
                ItensBoxCurso.put(0, "Selecione um Curso");
                ItensBoxCurso.put(c.getCod(), c.getNome_curso());
            }
        } catch (Exception ex) {
            System.out.println("Erro setBoxCurso: " + ex);
        }
    }

    private void setBoxDisciplina() {
//        ItensBoxDisciplina = new LinkedHashMap<>();
//        disciplina = new DisciplinaDAO().obterDisciplina();
//        ItensBoxDisciplina.clear();
//        for (Disciplina disc : disciplina) {
//            ItensBoxDisciplina.put("", "Selecione uma Disciplina");
//            if (disc.getFk_Curso_cod() == fk_Curso_cod) {
//                if (new MatriculaDisciplinaDAO().alunoMatriculado(fk_Aluno_cpf, disc.getCodigo())) {
//                    ItensBoxDisciplina.put(disc.getCodigo(), disc.getNome());
//                } else {
//                    ItensBoxDisciplina.remove(disc.getCodigo());
//                }
//            }
//        }
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
}
